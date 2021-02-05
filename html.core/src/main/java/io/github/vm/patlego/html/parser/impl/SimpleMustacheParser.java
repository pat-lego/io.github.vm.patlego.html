package io.github.vm.patlego.html.parser.impl;

import java.io.IOException;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.TemplateLoader;

import io.github.vm.patlego.html.Parseable;
import io.github.vm.patlego.html.parser.MustacheParser;

public class SimpleMustacheParser implements MustacheParser {

    @Override
    public String parse(String template, Parseable context, TemplateLoader loader) throws IOException {
        Handlebars handlebars = new Handlebars(loader);
        Template compiledTemplate = handlebars.compile(template); // resolved to: /WEB-INF/hello.html
        return compiledTemplate.apply(context.bean());
    }

}
