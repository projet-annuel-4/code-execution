package com.projet_annuel.rec.controller;

import com.google.gson.Gson;
import com.projet_annuel.rec.dto.CodeRequest;
import com.projet_annuel.rec.dto.CodeResponse;
import com.projet_annuel.rec.dto.CodeResponse2;
import com.projet_annuel.rec.repository.CodeResponseDao;
import com.projet_annuel.rec.service.RedisService;
import com.projet_annuel.rec.configuration.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/codeExecutor/v1")
public class CodeExecutionController {

    private final RedisService redisService;
    private final RpcClient rpcClient;
    private final CodeResponseDao codeResponseDao;
    public static final String HASH_KEY = "CodeResponse";
    private final Gson gson = new Gson();

    @Autowired
    public CodeExecutionController(final RedisService redisService, final RpcClient rpcClient, final CodeResponseDao codeResponseDao) {
        this.redisService = redisService;
        this.rpcClient = rpcClient;
        this.codeResponseDao = codeResponseDao;

    }

    @PostMapping("/submit")
    public ResponseEntity<CodeResponse2> saveCode(@RequestBody final CodeRequest code){
        System.out.println(code.toString());
        String response = rpcClient.send( code );
        //System.out.println( "ma reponse /submit : " + response);
        return getResults( response );
    }

    @GetMapping("/results/{id}")
    public ResponseEntity<CodeResponse2> getResults(@PathVariable final String id){
        System.out.println("id du get " + id);
        CodeResponse response = gson.fromJson( (String) redisService.getById(HASH_KEY, id ), CodeResponse.class);
        CodeResponse2 finalResponse = new CodeResponse2(response.getId(), response.getOutput(), response.getError(), response.getStatus());
        System.out.println(response);
        return ResponseEntity.ok(finalResponse);
    }
}
