package com.cjs.wymall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.cjs.wymall"})
public class WymallAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(WymallAdminApplication.class, args);
    }

}
