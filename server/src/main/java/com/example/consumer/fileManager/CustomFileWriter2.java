package com.example.consumer.fileManager;

import java.io.File;
import java.io.PrintWriter;

public interface CustomFileWriter2 {

    public void writeHeaderFile(PrintWriter printWriter);

    public void writeBodyFile(PrintWriter printWriter, String code);

    public void writeFooterFile(PrintWriter printWriter);

    public static void deleteFile(String path){
        File fileToDelete = new File(path);
        fileToDelete.delete();
    }
}
