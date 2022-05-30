package com.example.consumer.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Properties;
import java.util.Scanner;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.example.consumer.configuration")
public class RpcConfig implements CommandLineRunner {

    @Autowired
    private Environment env;

    public static final String directExchangeName = "rpc-exchange";
    public static final String queueName = "rpc-queue";
    public static final String routingKey = "rpc";

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(directExchangeName);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(RpcConfig.class, args).close();
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Waiting...");
        System.out.println( env.getProperty("cust.template.path"));
        //new Scanner(System.in).nextLine();
    }
}
