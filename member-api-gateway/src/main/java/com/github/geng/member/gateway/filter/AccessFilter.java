package com.github.geng.member.gateway.filter;

import com.github.geng.constant.RestResponseConstants;
import com.github.geng.exception.NotLoginException;
import com.github.geng.response.SysExceptionMsg;
import com.github.geng.token.config.UserTokenConfig;
import com.github.geng.token.info.UserTokenInfo;
import com.github.geng.token.util.JwtTokenManager;
import com.github.geng.util.JSONUtils;
import com.github.geng.util.SysStringUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

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
@Slf4j
public class AccessFilter extends ZuulFilter {

    @Value("${gate.ignore.startWith}")
    private String startWith;
    @Value("${zuul.prefix:null}")
    private String zuulPrefix;

    @Autowired
    private UserTokenConfig userTokenConfig; // 用户token 配置
    @Autowired
    private JwtTokenManager jwtTokenManager;

    @PostConstruct // 注解使用参考 https://blog.csdn.net/wo541075754/article/details/52174900
    public void init() {
        // 设置编码格式，获取类实例
        Feign.builder().encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder());
    }

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
        return 1;
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

    /**
     * 过滤器的具体逻辑
     * @return
     */
    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        String requestUri;
        if (null == zuulPrefix || "null".equals(zuulPrefix)) { // 没有前缀
            requestUri = request.getRequestURI();
        } else {
            requestUri = request.getRequestURI().substring(zuulPrefix.length());
        }
        // 拦截白名单
        if (null != startWith && SysStringUtil.isStartWith(requestUri, startWith.split(","))) {
            return null;
        }
        // 获取访问用户
        UserTokenInfo userTokenInfo;
        try {
            userTokenInfo = this.getUserFromRequest(request, context);
        } catch (Exception e) {
            // 返回自定义错误信息
            SysExceptionMsg message = new SysExceptionMsg(e.getMessage(),
                    System.currentTimeMillis(),
                    RestResponseConstants.USER_INVALID_TOKEN);
            context.setResponseBody(JSONUtils.createJson(message));
            context.setResponseStatusCode(RestResponseConstants.USER_INVALID_TOKEN);
            return null;
        }

        if (null == userTokenInfo || "".equals(userTokenInfo.getId())) {
            SysExceptionMsg message = new SysExceptionMsg("用户无效token",
                    System.currentTimeMillis(),
                    RestResponseConstants.USER_INVALID_TOKEN);
            context.setResponseBody(JSONUtils.createJson(message));
            context.setResponseStatusCode(RestResponseConstants.USER_INVALID_TOKEN);
            return null;
        }
        return true;
    }

    // =================================================================================
    // private methods
    // 获取用户信息
    private UserTokenInfo getUserFromRequest(HttpServletRequest request, RequestContext ctx) {
        String token = request.getHeader(userTokenConfig.getTokenHeader());
        if (!StringUtils.hasText(token)) {
            throw new NotLoginException("用户未登录");
        }
        ctx.addZuulRequestHeader(userTokenConfig.getTokenHeader(), token); // 传递token 信息
        return jwtTokenManager.getUserInfoFromToken(token);
    }

}
