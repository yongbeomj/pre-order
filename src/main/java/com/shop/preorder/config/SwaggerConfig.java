package com.shop.preorder.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .version("v1.0.0")
                .title("예약 주문 API")
                .description("");

        return new OpenAPI()
                .info(info);
    }

    @Bean
    public GroupedOpenApi getApi() {
        return GroupedOpenApi.builder()
                .group("all")
                .pathsToMatch("/api/**")
                .build();
    }

    @Bean
    public GroupedOpenApi getUserApi() {
        return GroupedOpenApi.builder()
                .group("users")
                .pathsToMatch("/api/users/**")
                .build();
    }

    @Bean
    public GroupedOpenApi getPostApi() {
        return GroupedOpenApi.builder()
                .group("posts")
                .pathsToMatch("/api/posts/**")
                .build();
    }

    @Bean
    public GroupedOpenApi getFollowApi() {
        return GroupedOpenApi.builder()
                .group("follows")
                .pathsToMatch("/api/follows/**")
                .build();
    }

    @Bean
    public GroupedOpenApi getReviewApi() {
        return GroupedOpenApi.builder()
                .group("reviews")
                .pathsToMatch("/api/reviews/**")
                .build();
    }
}
