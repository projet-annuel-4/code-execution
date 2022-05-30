package com.example.consumer.fileManager;

import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class JavaCustomFileWriter2 implements CustomFileWriter2 {

    private final String fileName = "C:\\Users\\LILOKE\\Desktop\\runDirectory\\JavaFile.java";

    @Override
    public void writeHeaderFile(PrintWriter printWriter) {
        printWriter.println("public class JavaFile {");
        printWriter.println("   public static void main(String[] args) {");
    }

    @Override
    public void writeBodyFile(PrintWriter printWriter, String code) {
        printWriter.println(code);
    }

    @Override
    public void writeFooterFile(PrintWriter printWriter) {
        printWriter.println("   }");
        printWriter.println("}");
    }

    public void buildFile(String code) throws IOException {
        PrintWriter printWriter = getWriter();
        writeHeaderFile(printWriter);
        writeBodyFile(printWriter, code);
        writeFooterFile(printWriter);
        closeWriter(printWriter);
    }

    private PrintWriter getWriter() throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);

        return new PrintWriter(fileWriter);
    }

    private void closeWriter(PrintWriter printWriter){
        printWriter.close();
    }

    public String getFileName() {
        return fileName;
    }
}
