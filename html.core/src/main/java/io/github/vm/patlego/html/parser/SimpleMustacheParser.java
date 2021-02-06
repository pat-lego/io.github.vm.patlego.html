package io.github.vm.patlego.html.parser;

import java.io.IOException;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.TemplateLoader;

import io.github.vm.patlego.html.Parseable;
import io.github.vm.patlego.html.exceptions.ParseableLoaderException;
import io.github.vm.patlego.html.parser.ParseableLoader;

public class SimpleMustacheParser {

    private SimpleMustacheParser() {
        throw new IllegalStateException("Utility class");
    }

    public static String parse(String template, TemplateLoader loader, ParseableLoader parseableLoader)
            throws IOException, ParseableLoaderException {
        Handlebars handlebars = new Handlebars(loader);
        Template compiledTemplate = handlebars.compile(template);
        Parseable parseable = parseableLoader.getParseable(template + loader.getSuffix());

        return compiledTemplate.apply(parseable.bean());
    }
}
