package com.jim.universal_templates;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jim.universal_templates.mapper")
public class UniversalTemplatesApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniversalTemplatesApplication.class, args);
    }

}
