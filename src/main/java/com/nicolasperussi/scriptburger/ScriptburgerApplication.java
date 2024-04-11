package com.nicolasperussi.scriptburger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.nicolasperussi.scriptburger")
@OpenAPIDefinition(info = @Info(title = "Script Burger API", version = "1", description = "API desenvolvida para o sistema do Script Burger"))
public class ScriptburgerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScriptburgerApplication.class, args);
    }

}
