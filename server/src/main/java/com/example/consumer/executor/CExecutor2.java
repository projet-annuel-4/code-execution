package com.example.consumer.executor;

import com.example.consumer.dto.CodeRequest;
import com.example.consumer.dto.CodeResponse;
import com.example.consumer.fileManager.CCustomFileWriter2;
import com.example.consumer.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

@Component
public class CExecutor2 implements CodeExecutor2 {

    @Autowired
    RedisService redisService;
    @Autowired
    CCustomFileWriter2 cCustomFileWriter;


    @Override
    public CodeResponse executeCode(CodeRequest codeRequest) {
        try {
            cCustomFileWriter.buildFile(codeRequest.getCode());
            Process process = Runtime.getRuntime()
                    .exec("gcc -o C:\\Users\\LILOKE\\Desktop\\runDirectory\\cFile C:\\Users\\LILOKE\\Desktop\\runDirectory\\cFile.c");
            process = Runtime.getRuntime()
                    .exec("C:\\Users\\LILOKE\\Desktop\\runDirectory\\cFile.exe");

            Map<String, ArrayList<String>> result = ProcessManager.getProcessResult(process);

            return new CodeResponse(UUID.randomUUID().toString(), result.get("outputs"), result.get("errors"), "done");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
