package com.example.consumer.dto;

import java.util.ArrayList;

public class Template {

    private String extension;
    private ArrayList<String> commandsToExecute;
    private String strToReplaceInFile;

    public Template(String extension, ArrayList<String> commandsToExecute, String strToReplaceInFile) {
        this.extension = extension;
        this.commandsToExecute = commandsToExecute;
        this.strToReplaceInFile = strToReplaceInFile;
    }
    public Template() {

    }



    public String getExtension() {
        return extension;
    }

    public ArrayList<String> getCommandsToExecute() {
        return commandsToExecute;
    }

    public String getStrToReplaceInFile() {
        return strToReplaceInFile;
    }
}
