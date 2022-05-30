package com.example.consumer.executor;

import com.example.consumer.dto.CodeRequest;
import com.example.consumer.dto.CodeResponse;
import com.example.consumer.fileManager.JavaCustomFileWriter2;
import com.example.consumer.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

@Component
public class JavaExecutor2 implements CodeExecutor2 {

    @Autowired
    RedisService redisService;
    @Autowired
    JavaCustomFileWriter2 javaCustomFileWriter;


    @Override
    public CodeResponse executeCode(CodeRequest codeRequest) {
        try {
            javaCustomFileWriter.buildFile(codeRequest.getCode());
            Process process = Runtime.getRuntime()
                    .exec("javac C:\\Users\\LILOKE\\Desktop\\runDirectory\\JavaFile.java");
            process = Runtime.getRuntime()
                    .exec("java -cp C:\\Users\\LILOKE\\Desktop\\runDirectory JavaFile.class");

            Map<String, ArrayList<String>> result = ProcessManager.getProcessResult(process);

            return new CodeResponse(UUID.randomUUID().toString(), result.get("outputs"), result.get("errors"), "done");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return null;
    }
}
