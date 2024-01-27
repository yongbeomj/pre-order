package com.shop.preorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PreOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(PreOrderApplication.class, args);
    }

}
