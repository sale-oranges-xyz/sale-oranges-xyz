package com.github.geng.admin.gateway.filter;

import com.github.geng.admin.gateway.config.UrlMatcher;
import com.github.geng.admin.redis.config.AdminUserRedisConfig;
import com.github.geng.admin.redis.entity.RedisRole;
import com.github.geng.admin.redis.template.PermissionRoleTemplate;
import com.github.geng.admin.redis.template.UserRoleTemplate;
import com.github.geng.constant.DataConstants;
import com.github.geng.constant.ResponseConstants;
import com.github.geng.gateway.filter.AbstractAccessFilter;
import com.github.geng.token.config.UserTokenConfig;
import com.github.geng.token.info.TokenInfo;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Set;



/**
 * <pre>
 *  在完成了服务路由之后，我们对外开放服务还需要一些安全措施来保护客户端只能访问它应该访问到的资源。
 *  所以我们需要利用Zuul的过滤器来实现我们对外服务的安全控制。
 *  在服务网关中定义过滤器只需要继承ZuulFilter抽象类实现其定义的四个抽象函数就可对请求进行拦截与过滤
 *  全局异常处理，参考 http://blog.didispace.com/spring-cloud-zuul-exception/
 * </pre>
 * @author geng
 */
@Component //注入spring容器，即实例化该过滤链，否则不会生效
public class AccessFilter extends AbstractAccessFilter {

    private UserTokenConfig userTokenConfig;
    private PermissionRoleTemplate permissionRoleTemplate;
    private UserRoleTemplate userRoleTemplate;
    private UrlMatcher urlMatcher;
    // @PostConstruct // 注解使用参考 https://blog.csdn.net/wo541075754/article/details/52174900
    // public void init() {
        //InstanceInfo prodSvcInfo = discoveryClient.getNextServerFromEureka("admin-users", false);
        // 设置编码格式，获取类实例
        // Feign.builder().encoder(new JacksonEncoder()).decoder(new JacksonDecoder());
                //.requestInterceptor(clientFeignInterceptor)
                //.target(AdminService.class, prodSvcInfo.getHomePageUrl());
    // }
    /**
     * filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
     * pre：可以在请求被路由之前调用
     * routing：在路由请求时候被调用
     * post：在routing和error过滤器之后被调用
     * error：处理请求时发生错误时被调用
     * @return 请求被路由调用位置
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 通过int值来定义过滤器的执行顺序
     * @return 执行顺序
     */
    @Override
    public int filterOrder() {
        return 6;
    }

    /**
     * 返回一个boolean类型来判断该过滤器是否要执行，<br>
     * 所以通过此函数可实现过滤器的开关。
     * @return true 过滤器总是生效 | false 不执行
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    // ---------------------------------------------------------------------
    // 真正业务逻辑
    @Override
    protected String getTokenHeader() {
        return userTokenConfig.getHeader();
    }

    @Override
    protected String getRealToken(String tokenHeader) {
        return userTokenConfig.getToken(tokenHeader);
    }

    @Override
    protected void validateTokenInfo(TokenInfo tokenInfo, String requestUri, String requestMethod, RequestContext context) {
        // 这里的requestUri是zuul 添加代理前缀后的信息，需要去掉
//        String proxy = (String)context.get(PROXY_KEY);
//        if (StringUtils.hasText(proxy)) {
//            requestUri = requestUri.substring(String.format("/%s", proxy).length());
//        }
        // 获取用户权限
        // 这里的requestUri需要处理，因为是restful风格，部分内容需要还原
        String patternUrl = urlMatcher.findPattern(requestUri);
        // 根据权限查找角色
        String permissionKey = AdminUserRedisConfig.createPermissionKey(patternUrl, requestMethod);
        // 根据用户查找角色
        String adminKey = AdminUserRedisConfig.createAdminKey(tokenInfo.getId());

        // 用户权限检查,控制到url与方法访问方法匹配级别
        // 判断两个集合是否有相同的角色, 利用redis set 集合取交集做判断
        Set<RedisRole> intersectRedisRole = this.permissionRoleTemplate
                .opsForSet()
                .intersect(permissionKey, adminKey);// 取交集

        if (CollectionUtils.isEmpty(intersectRedisRole)) { // 没有数据
            logger.debug("用户:{}无uri:{},method:{}访问权限", tokenInfo.getName(), requestUri, requestMethod);
            super.sendErrMsg(DataConstants.USER_FORBIDDEN,
                    ResponseConstants.USER_FORBIDDEN_TOKEN,
                    context);
        }
    }

    // ---------------------------------------------------------------------------------------
    // setters
    @Autowired
    public void setUserTokenConfig(UserTokenConfig userTokenConfig) {
        this.userTokenConfig = userTokenConfig;
    }
    @Autowired
    public void setUserRoleTemplate(UserRoleTemplate userRoleTemplate) {
        this.userRoleTemplate = userRoleTemplate;
    }
    @Autowired
    public void setPermissionRoleTemplate(PermissionRoleTemplate permissionRoleTemplate) {
        this.permissionRoleTemplate = permissionRoleTemplate;
    }
    @Autowired
    public void setUrlMatcher(UrlMatcher urlMatcher) {
        this.urlMatcher = urlMatcher;
    }
}
