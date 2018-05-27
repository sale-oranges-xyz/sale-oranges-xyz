package com.github.geng.auth.client.interceptor;

import com.github.geng.auth.client.configuration.FeignIgnore;
import com.github.geng.auth.client.schedule.ClientAuthSchedule;
import com.github.geng.bread.NullOptional;
import com.github.geng.constant.DataConstant;
import com.github.geng.token.config.ClientTokenConfig;
import com.github.geng.token.config.UserTokenConfig;
import com.github.geng.util.RequestUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 服务端使用feign时，写入请求头信息，确保服务端的安全
 * @author geng
 */
@Slf4j
@Component
public class ClientFeignInterceptor implements RequestInterceptor {
    @Autowired
    private ClientTokenConfig clientTokenConfig;
    @Autowired
    private ClientAuthSchedule clientAuthSchedule;
    @Autowired
    private FeignIgnore feignIgnore;

    @Override
    public void apply(RequestTemplate template) {
        // 传递请求头信息
        HttpServletRequest request = RequestUtils.getRequest();
        if (null != request) {
            Enumeration<String> headerNames = request.getHeaderNames();
            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    String name = headerNames.nextElement();
                    String values = request.getHeader(name);
                    template.header(name, values);
                }
            }
        }
        //System.out.println("url" + template.url());
        // 不需要添加请求头url 处理
        if (!feignIgnore.isMatch(template.url())) {
            template.header(clientTokenConfig.getTokenHeader(), clientAuthSchedule.getClientToken());
            template.header(DataConstant.CLIENT_NAME,clientTokenConfig.getApplicationName());
            template.header(DataConstant.IS_CLIENT, DataConstant.FROM_CLIENT);
        }
    }

}
