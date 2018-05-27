package com.github.geng.auth.center.runner;

import com.github.geng.auth.center.configuration.ClientKeyConfig;
import com.github.geng.auth.center.dto.ClientForm;
import com.github.geng.auth.center.service.ClientAuthService;
import com.github.geng.auth.client.entity.event.AllowedClient;
import com.github.geng.event.EventPublish;
import com.github.geng.token.config.ClientTokenConfig;
import com.github.geng.token.util.RsaKeyManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 系统初始化时获取公钥与私钥
 * <pre>
 *  spring boot 的 CommandLineRunner 使用
 *  参考: http://rensanning.iteye.com/blog/2363313
 *  说白了，就是发布订阅模式的一个使用
 * </pre>
 * 注意：这种类型的自定义的事件一定要先注入spring 的 bean 容器
 * @author geng
 */
@Slf4j
@Component
public class AuthClientRunner implements CommandLineRunner {

    @Autowired
    private ClientKeyConfig clientKeyConfig;
    @Autowired
    private ClientAuthService clientAuthService;
    @Autowired
    private ClientTokenConfig clientTokenConfig; // token 配置
    @Autowired
    private ApplicationContext context;

    @Override
    public void run(String... strings) throws Exception {
        Map<String, byte[]> keyMap = RsaKeyManager.generateKey(clientTokenConfig.getClientSecret());
        clientKeyConfig.setServicePubKey(keyMap.get("public"));
        clientKeyConfig.setServicePriKey(keyMap.get("private"));
        // 产生token
        log.debug("系统初始化成功，开始产生token");
        clientAuthService.createToken();

        ClientForm clientForm = new ClientForm(clientTokenConfig.getClientId(), clientTokenConfig.getClientSecret());
        AllowedClient allowedClient = new AllowedClient(clientAuthService.allowedClient(clientForm));
        log.debug("系统初始化成功,获取可访问远程调用客户端列表,发送事件");
        new EventPublish(context, this, allowedClient).publish();
    }

}
