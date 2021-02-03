package io.github.vm.patlego.html.parser.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import io.github.vm.patlego.html.Parseable;
import io.github.vm.patlego.html.parser.MustacheParser;

public class SimpleMustacheParser implements MustacheParser {

    @Override
    public String parse(String template, Parseable context) throws IOException {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache m = mf.compile(template);

        Writer writer = new StringWriter();
        m.execute(writer, context.bean()).flush();
        return writer.toString();
    }
    
}
