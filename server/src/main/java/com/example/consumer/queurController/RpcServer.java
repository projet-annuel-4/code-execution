package com.example.consumer.queurController;

import com.example.consumer.TemplateManager;
import com.example.consumer.configuration.RpcConfig;
import com.example.consumer.dto.CodeRequest;
import com.example.consumer.dto.CodeResponse;
import com.example.consumer.dto.Template;
import com.example.consumer.executor.*;
import com.example.consumer.service.RedisService;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

@Component
class RpcServer {

    @Autowired
    RedisService redisService;
    @Autowired
    CodeExecutor codeExecutor;

    @SneakyThrows
    @RabbitListener(queues = RpcConfig.queueName)
    public String ExecuteCode(String request) throws InterruptedException {
        System.out.println("Received: " + request);
        //Long running processing
        Gson gson = new Gson();
        CodeRequest codeRequest = gson.fromJson(request, CodeRequest.class);
        System.out.println(codeRequest.toString());
        //TimeUnit.SECONDS.sleep(5);
        //System.out.println("request : " + request.getCode());
        String language = codeRequest.getLanguage();
        CodeResponse response = null;
        System.out.println("langage : " + language);
        System.out.println(" test : " + Objects.equals(language, "java"));

        response = codeExecutor.executeCode(codeRequest);

        if( response == null){

            response = new CodeResponse(UUID.randomUUID().toString(),
                    new ArrayList<>(),
                    new ArrayList<>(Arrays.asList("une erreur est survenue")),
                    "failed");
        }

        redisService.save(response.getId(), gson.toJson(response));
        return response.getId();

    }
}

