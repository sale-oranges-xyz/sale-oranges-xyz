package com.github.geng.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author geng
 */
@Configuration
// @EnableSwagger2 该标签需要在spring boot启动类使用
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //暂时不考虑 dto数据转换
                .apis(RequestHandlerSelectors.basePackage("com.github.geng.*.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Restful Apis")
                .description("Restful Apis")
                //.termsOfServiceUrl("")
                .version("1.0")
                .build();
    }
}
