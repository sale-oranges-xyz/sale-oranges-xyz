package com.github.geng.security.filter;

import com.github.geng.auth.client.schedule.ClientAuthSchedule;
import com.github.geng.constant.DataConstant;
import com.github.geng.exception.ErrorMsg;
import com.github.geng.security.entity.AuthorizeIgnore;
import com.github.geng.security.extra.AuthenticationSerious;
import com.github.geng.token.config.ClientTokenConfig;
import com.github.geng.token.config.UserTokenConfig;
import com.github.geng.token.util.JwtTokenManager;
import com.github.geng.util.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * token 过滤器
 * @author geng
 */
@Slf4j
public class AuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {
    @Autowired
    private UserTokenConfig userTokenConfig; // 用户端token
    @Autowired
    private ClientTokenConfig clientTokenConfig; // 客户端token
    @Autowired
    private ClientAuthSchedule clientAuthSchedule;
    @Autowired
    private AuthorizeIgnore authorizeIgnore;
    @Autowired
    private AuthenticationSerious authenticationSerious;

    // TODO 暂时不做微服务间的权限管理 , 注释部分功能未完成
    // 以后再简化这里的处理，因为这里的拦截白名单与 WebSecurityConfig 配置的拦截白名单一致
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 将 ServletRequest 转换为 HttpServletRequest 才能拿到请求头中的 token
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        // 白名单过滤
        if (authorizeIgnore.isMatch(httpRequest.getRequestURI())) {
            chain.doFilter(request, response);
            return;
        }
        // 尝试获取请求头的 token
        String userAuthToken = httpRequest.getHeader(userTokenConfig.getTokenHeader());
        String clientAuthToken = httpRequest.getHeader(clientTokenConfig.getTokenHeader());
        // 没有用户token和服务端token, 不需要验证的链接需要在WebSecurityConfig中配置
        if (!StringUtils.hasText(userAuthToken) && !StringUtils.hasText(clientAuthToken)) {
            log.debug("无法从请求路径:{}获取token", httpRequest.getRequestURI());
            this.sendErrorMsg(response);
        } else {
            // 区分是用户token还是服务端token
            // 微服务间的远程调用
            if (DataConstant.FROM_CLIENT.equals(httpRequest.getHeader(DataConstant.IS_CLIENT))) {
                log.debug("服务端远程调用鉴权开始");
                //获取当前服务可访问列表，与请求微服务名称比较
                // List<String> allowedClient = clientAuthSchedule.getAllowedClient();
                String headerClientName = httpRequest.getHeader(DataConstant.CLIENT_NAME);

                if (clientTokenConfig.getApplicationName().equals(headerClientName)) {
                    log.debug("当前服务:{}访问自身服务实例,放行", headerClientName);
                    chain.doFilter(request, response);
                    return;
                }

//                boolean result = allowedClient.stream().anyMatch(clientName -> clientName.equals(headerClientName));
//                log.debug("当前服务:{} 开始判断远程调用服务:{} 是否有访问权限。结果:{}",
//                        clientTokenConfig.getApplicationName(), headerClientName, result);

                // 调用微服务无权限访问该微服务,获取token
//                if (!result || !clientAuthSchedule.getClientToken().equals(clientAuthToken)) {
//                    log.debug("微服务:{}无权访问微服务:{},或者token无效", headerClientName, clientTokenConfig.getApplicationName());
//                    this.sendErrorMsg(response);
//                    return;
//                }
                if (!clientAuthSchedule.getClientToken().equals(clientAuthToken)) {
                    log.debug("微服务:{} token无效", headerClientName, clientTokenConfig.getApplicationName());
                    this.sendErrorMsg(response);
                    return;
                }
                chain.doFilter(request, response);
            } else { // 用户 token 检查
                // 看能不能根据token解析出用户
                try {
                    // 验证接口
                    authenticationSerious.authentication(userAuthToken);
                    chain.doFilter(request, response);
                } catch (Exception e) {
                    String errorLog = "解析用户token:" + userAuthToken + "异常";
                    log.error(errorLog, e);
                    this.sendErrorMsg(response);
                }
            }
        }
    }


    private void sendErrorMsg (ServletResponse response) throws IOException{
         response.setCharacterEncoding("UTF-8");
         response.setContentType("application/json;charset=UTF-8");
         ErrorMsg errorMsg = new ErrorMsg("无效token", HttpStatus.FORBIDDEN.value());
         response.getWriter().print(JSONUtils.createJson(errorMsg));
        response.getWriter().flush();
        // 抛出服务端异常
        // throw new ServiceException("无效token", HttpStatus.FORBIDDEN.value());
    }

}
