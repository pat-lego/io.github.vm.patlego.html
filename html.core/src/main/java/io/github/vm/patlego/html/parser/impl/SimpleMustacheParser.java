package io.github.vm.patlego.html.parser.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.TemplateLoader;

import io.github.vm.patlego.html.Parseable;
import io.github.vm.patlego.html.parser.MustacheParser;
import io.github.vm.patlego.html.parser.ParseableLoader;

public class SimpleMustacheParser implements MustacheParser {

    @Override
    public String parse(String template, TemplateLoader loader, ParseableLoader parseableLoader)
            throws IOException, IllegalAccessException, InvocationTargetException {
        Handlebars handlebars = new Handlebars(loader);
        Template compiledTemplate = handlebars.compile(template);
        Parseable parseable = parseableLoader.getParseable(template + loader.getSuffix());
        
        return compiledTemplate.apply(parseable.bean());
    }
}
