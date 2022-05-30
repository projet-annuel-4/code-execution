package com.example.consumer;

import com.example.consumer.dto.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.*;

@Component
public class TemplateManager {

    @Autowired
    private static Environment env;

// generaliser a une seule fonction quand y'aura la bdd

    public static Template getTemplate(String language){

        Template template = new Template();
        if(Objects.equals(language, "java")){
            template = getJavaTemplate();
        } else if ( Objects.equals(language, "c")){
            template = getCTemplate();
        } else if ( Objects.equals(language, "javascript")){
            template = getJavascriptTemplate();
        } else if ( Objects.equals(language, "python")){
            template = getPythonTemplate();
        } else {
            template = null;
        }
        return template;
    }

    private static Template getJavaTemplate(){

        return new Template("java",
                new ArrayList<>(
                        Arrays.asList(
                                "javac C:\\Users\\LILOKE\\Desktop\\ProjetAnnuelDirectory\\runDirectory\\template.java",
                                "java -cp C:\\Users\\LILOKE\\Desktop\\ProjetAnnuelDirectory\\runDirectory template.class"
                        )
                ),
                "###CODE###"
        );
    }

    private static Template getJavascriptTemplate(){

        return new Template("js",
                new ArrayList<>(
                        List.of(
                                "node C:\\Users\\LILOKE\\Desktop\\ProjetAnnuelDirectory\\runDirectory\\template.js"
                        )
                ),
                "###CODE###"
        );
    }

    private static Template getPythonTemplate(){

        return new Template("py",
                new ArrayList<>(
                        List.of(
                                "python C:\\Users\\LILOKE\\Desktop\\ProjetAnnuelDirectory\\runDirectory\\template.py"
                        )
                ),
                "###CODE###"
        );
    }

    private static Template getCTemplate(){

        return new Template("c",
                new ArrayList<>(
                        List.of(
                                "gcc -o C:\\Users\\LILOKE\\Desktop\\ProjetAnnuelDirectory\\runDirectory\\template C:\\Users\\LILOKE\\Desktop\\ProjetAnnuelDirectory\\runDirectory\\template.c",
                                "C:\\Users\\LILOKE\\Desktop\\ProjetAnnuelDirectory\\runDirectory\\template"
                        )
                ),
                "###CODE###"
        );
    }

}
