package com.example.consumer.executor;

import com.example.consumer.dto.CodeRequest;
import com.example.consumer.dto.CodeResponse;
import com.example.consumer.fileManager.JavascriptCustomFileWriter2;
import com.example.consumer.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

@Component
public class JavascriptExecutor2 implements CodeExecutor2 {

    @Autowired
    RedisService redisService;
    @Autowired
    JavascriptCustomFileWriter2 javascriptCustomFileWriter;

    @Override
    public CodeResponse executeCode(CodeRequest codeRequest) {
        try {
            javascriptCustomFileWriter.buildFile(codeRequest.getCode());
            Process process = Runtime.getRuntime()
                    .exec("node C:\\Users\\LILOKE\\Desktop\\runDirectory\\javascriptFile.js");

            Map<String, ArrayList<String>> result = ProcessManager.getProcessResult(process);

            return new CodeResponse(UUID.randomUUID().toString(), result.get("outputs"), result.get("errors"), "done");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
