package com.projet_annuel.rec.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeRequest implements Serializable {

    private String code;
    private String language;
    private ArrayList<String> input;
    private CompilationMode mode;

    public CodeRequest(String code) {
        this.code = code;
    }

    public CodeRequest(String code, String language, ArrayList<String> input) {
        this.code = code;
        this.language = language;
        this.input = input;
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
}
