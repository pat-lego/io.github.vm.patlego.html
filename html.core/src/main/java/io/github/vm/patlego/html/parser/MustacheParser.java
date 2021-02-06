package io.github.vm.patlego.html.parser;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import com.github.jknack.handlebars.io.TemplateLoader;

public interface MustacheParser {

    public String parse(String template, TemplateLoader loader, ParseableLoader parseable) throws IOException, IllegalAccessException, InvocationTargetException;
    
}
