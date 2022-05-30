package com.example.consumer.executor;

import com.example.consumer.dto.CodeRequest;
import com.example.consumer.dto.CodeResponse;

public interface CodeExecutor2 {

    CodeResponse executeCode(CodeRequest codeRequest);
}
