package com.cjs.wymall.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.cjs.wymall"})
public class WymallPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(WymallPortalApplication.class, args);
    }

}
