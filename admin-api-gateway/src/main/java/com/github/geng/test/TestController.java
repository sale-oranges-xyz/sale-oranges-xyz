package com.github.geng.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author geng
 */
@RestController
@RefreshScope // 加入RefreshScope 否则无法刷新
public class TestController {

    @Value("${test: default}")
    private String msg;

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String test001() {
        return msg;
    }

}
