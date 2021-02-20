package io.github.vm.patlego.html.parser;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.helper.ConditionalHelpers;
import com.github.jknack.handlebars.io.TemplateLoader;

import io.github.vm.patlego.html.Parseable;
import io.github.vm.patlego.html.exceptions.ParseableLoaderException;

public class SimpleMustacheParser {

    private SimpleMustacheParser() {
        throw new IllegalStateException("Utility class");
    }

    private static String parse(String template, Handlebars handlebars, TemplateLoader loader, ParseableLoader parseableLoader)
            throws IOException, ParseableLoaderException {
        Template compiledTemplate = handlebars.compile(template);
        Parseable parseable = parseableLoader.getParseable(template + loader.getSuffix());

        return compiledTemplate.apply(parseable.bean());
    }

    public static String parse(String template, TemplateLoader loader, ParseableLoader parseableLoader)
            throws IOException, ParseableLoaderException {
        Handlebars handlebars = new Handlebars(loader);
        return SimpleMustacheParser.parse(template, handlebars, loader, parseableLoader);
    }

    public static String parse(String template, TemplateLoader loader, ParseableLoader parseableLoader, Object... helpers)
            throws IOException, ParseableLoaderException {
        Handlebars handlebars = new Handlebars(loader);
        if (helpers != null && helpers.length > 0) {
            for (Object helper : helpers) {
                handlebars = handlebars.registerHelpers(helper);
            }
            
        }
        return SimpleMustacheParser.parse(template, handlebars, loader, parseableLoader);
    }
}
