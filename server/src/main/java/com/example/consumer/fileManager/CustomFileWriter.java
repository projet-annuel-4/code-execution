package com.example.consumer.fileManager;

import com.example.consumer.dto.Template;
import org.apache.logging.log4j.util.Chars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class CustomFileWriter {

    @Autowired
    Environment env;

    public void copyTemplateToRunDirectory(String fileExtension) throws IOException {
        Path copyFileDestination = Paths.get( env.getProperty("cust.runDirectory.path") + "\\template." + fileExtension );
        Path fileCopied = Paths.get( env.getProperty("cust.template.path") + "\\template." + fileExtension );
        Files.copy(fileCopied, copyFileDestination, StandardCopyOption.REPLACE_EXISTING);
    }

    public void rewriteFile(Template template, String code) throws IOException {
        System.out.println(env.getProperty("cust.runDirectory.path") + "\\template." + template.getExtension());
        String pathToRewrite = env.getProperty("cust.runDirectory.path") + "\\template." + template.getExtension();
        Path path = Paths.get(pathToRewrite);
        Charset charset = StandardCharsets.UTF_8;

        String content = Files.readString(path, charset);
        content = content.replaceAll("###CODE###", code);
        Files.writeString(path, content, charset);
    }

    public void deleteExecutedFile(){

    }

}
