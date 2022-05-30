package com.example.consumer.fileManager;

import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

@Component
public class CCustomFileWriter2 implements CustomFileWriter2 {

    private final String fileName = "C:\\Users\\LILOKE\\Desktop\\runDirectory\\cFile.c";

    @Override
    public void writeHeaderFile(PrintWriter printWriter) {
        ArrayList<String> includes = getStandardIncludes();
        for (String include : includes) {
            printWriter.println("#include " + include);
        }
        printWriter.println("int main ( int argc , char * argv[] ) {");
    }

    @Override
    public void writeBodyFile(PrintWriter printWriter, String code) {
        printWriter.println(code);
    }

    @Override
    public void writeFooterFile(PrintWriter printWriter) {
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

    private ArrayList<String> getStandardIncludes(){
        return new ArrayList<>(Arrays.asList("<assert.h>",
                                             "<ctype.h>",
                                             "<locale.h>",
                                             "<math.h>",
                                             "<setjmp.h>",
                                             "<signal.h>",
                                             "<stdarg.h>",
                                             "<stdio.h>",
                                             "<stdlib.h>",
                                             "<string.h>",
                                             "<time.h>"));
    }

    public String getFileName() {
        return fileName;
    }
}
