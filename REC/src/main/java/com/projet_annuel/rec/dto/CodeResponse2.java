package com.projet_annuel.rec.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.ArrayList;



public class CodeResponse2 {

    final private String id;
    final private ArrayList<String> output;
    final private ArrayList<String> error;
    final private String status;

    public CodeResponse2(String id, ArrayList<String> output, ArrayList<String> error, String status) {
        this.id = id;
        this.output = output;
        this.error = error;
        this.status = status;
    }

    @Override
    public String toString() {
        return "CodeResponse{" +
                "id='" + id + '\'' +
                ", output=" + output +
                ", error=" + error +
                ", status='" + status + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public ArrayList<String> getOutput() {
        return output;
    }

    public ArrayList<String> getError() {
        return error;
    }

    public String getStatus() {
        return status;
    }
}
