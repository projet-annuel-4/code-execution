package com.projet_annuel.rec.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class CodeDto implements Serializable {

    private String id;
    private String content;
    private String language;
    private ArrayList<String> inputs;


    public CodeDto(String id, String content, String language, ArrayList<String> inputs) {
        this.id = id;
        this.content = content;
        this.language = language;
        this.inputs = inputs;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
