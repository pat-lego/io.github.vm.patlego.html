package io.github.vm.patlego.html.parser;

import java.io.IOException;

import io.github.vm.patlego.html.Parseable;

public interface MustacheParser {

    public String parse(String template, Parseable context) throws IOException;
    
}
