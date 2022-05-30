package com.example.consumer.executor;

import com.example.consumer.dto.CodeRequest;
import com.example.consumer.dto.CodeResponse;
import com.example.consumer.fileManager.Python2CustomFileWritter;
import com.example.consumer.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

@Component
public class PythonExecutor2 implements CodeExecutor2 {

    @Autowired
    RedisService redisService;
    @Autowired
    Python2CustomFileWritter pythonCustomFileWritter;

    @Override
    public CodeResponse executeCode(CodeRequest codeRequest) {

        try {
            pythonCustomFileWritter.buildFile(codeRequest.getCode());
            Process process = Runtime.getRuntime()
                    .exec("python C:\\Users\\LILOKE\\Desktop\\runDirectory\\pythonFile.py");

            Map<String, ArrayList<String>> result = ProcessManager.getProcessResult(process);

            return new CodeResponse(UUID.randomUUID().toString(), result.get("outputs"), result.get("errors"), "done");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
