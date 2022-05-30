package com.example.consumer.executor;

import com.example.consumer.TemplateManager;
import com.example.consumer.dto.CodeRequest;
import com.example.consumer.dto.CodeResponse;
import com.example.consumer.dto.Template;
import com.example.consumer.fileManager.CustomFileWriter;
import com.example.consumer.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

@Component
public class CodeExecutor {

    @Autowired
    RedisService redisService;

    @Autowired
    CustomFileWriter customFileWriter;

    public CodeResponse executeCode(CodeRequest codeRequest) throws IOException {

        Template template = TemplateManager.getTemplate(codeRequest.getLanguage());

        this.customFileWriter.copyTemplateToRunDirectory(template.getExtension());
        this.customFileWriter.rewriteFile(template, codeRequest.getCode());

        Process process = null;

//        for (String command : template.getCommandsToExecute()){
//            process = Runtime.getRuntime().exec( command );
//        }

        process = Runtime.getRuntime().exec("mvn C:/Users/LILOKE/Desktop/PC_save_1/4eme_AL/Projet Annuel/server");
        process = Runtime.getRuntime().exec("mvn clean");
        Map<String, ArrayList<String>> result = ProcessManager.getProcessResult(process);

        return new CodeResponse(UUID.randomUUID().toString(), result.get("outputs"), result.get("errors"), "done");

    }


}
