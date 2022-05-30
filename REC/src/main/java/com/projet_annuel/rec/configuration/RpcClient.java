package com.projet_annuel.rec.configuration;

import com.google.gson.Gson;
import com.projet_annuel.rec.configuration.RpcConfig;
import com.projet_annuel.rec.dto.CodeRequest;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public
class RpcClient {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private DirectExchange exchange;

    public String send(CodeRequest request) {

        template.setReplyTimeout(60000);
        Gson gson = new Gson();
        System.out.println("Sending name: " + gson.toJson(request));
        String response = (String) template.convertSendAndReceive(exchange.getName(), RpcConfig.routingKey, gson.toJson(request));
        return response;
    }
}