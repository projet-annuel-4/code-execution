package com.projet_annuel.rec.configuration;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.Scanner;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.projet_annuel.rec.configuration")
public class RpcConfig implements CommandLineRunner {

    public static final String directExchangeName = "rpc-exchange";
    public static final String routingKey = "rpc";

    @Autowired
    @Lazy
    RpcClient rpcClient;

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(directExchangeName);
    }

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(RpcConfig.class, args).close();
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
