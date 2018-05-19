package com.github.geng.auth.center.runner;

import com.github.geng.auth.center.configuration.ClientKeyConfig;
import com.github.geng.token.util.RsaKeyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.Map;

/**
 * 系统初始化时获取公钥与私钥
 * <pre>
 *  spring boot 的 CommandLineRunner 使用
 *  参考: http://rensanning.iteye.com/blog/2363313
 *  说白了，就是发布订阅模式的一个使用
 * </pre>
 * @author geng
 */
public class AuthClientRunner implements CommandLineRunner {

    @Autowired
    private ClientKeyConfig clientKeyConfig;

    @Override
    public void run(String... strings) throws Exception {
        Map<String, String> keyMap = RsaKeyManager.generateKey(clientKeyConfig.getServiceSecret());
        clientKeyConfig.setServicePubKey(keyMap.get("public"));
        clientKeyConfig.setServicePriKey(keyMap.get("private"));
    }

}
