package com.github.geng.auth.client.interceptor;

import com.github.geng.auth.client.schedule.ClientAuthSchedule;
import com.github.geng.token.config.ClientTokenConfig;
import com.github.geng.token.config.UserTokenConfig;
import com.github.geng.util.RequestUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 服务端使用feign时，写入请求头信息，确保服务端的安全
 * @author geng
 */
@Slf4j
public class ClientFeignInterceptor implements RequestInterceptor {

    private ClientTokenConfig clientTokenConfig;
    private ClientAuthSchedule clientAuthSchedule;

    public ClientFeignInterceptor(ClientTokenConfig clientTokenConfig,
                                  ClientAuthSchedule clientAuthSchedule) {
            this.clientTokenConfig = clientTokenConfig;
            this.clientAuthSchedule = clientAuthSchedule;
    }

    @Override
    public void apply(RequestTemplate template) {
        template.header(clientTokenConfig.getTokenHeader(), clientAuthSchedule.getClientToken());
        // 传递请求头信息
        HttpServletRequest request = RequestUtils.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String values = request.getHeader(name);
                template.header(name, values);
            }
        }
    }


}
