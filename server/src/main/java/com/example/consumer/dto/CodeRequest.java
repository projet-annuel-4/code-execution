package com.example.consumer.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class CodeRequest implements Serializable {

    private String code;
    private String language;
    private ArrayList<String> input;
    private CompilationMode mode;

    public CodeRequest(String code, String language, ArrayList<String> input, CompilationMode mode) {
        this.code = code;
        this.language = language;
        this.input = input;
        this.mode = mode;
    }

    public String getCode() {
        return code;
    }

    public String getLanguage() {
        return language;
    }

    public ArrayList<String> getInput() {
        return input;
    }

    public CompilationMode getMode() {
        return mode;
    }

    @Override
    public String toString() {
        return "CodeRequest{" +
                "code='" + code + '\'' +
                ", language='" + language + '\'' +
                ", input=" + input +
                ", mode=" + mode +
                '}';
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setInput(ArrayList<String> input) {
        this.input = input;
    }

    public void setMode(CompilationMode mode) {
        this.mode = mode;
    }
}
