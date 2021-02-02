package ioio.github.vm.patlego.html.parser;

import ioio.github.vm.patlego.html.Parseable;

public interface MustacheParser {

    public String parse(String template, Parseable context);
    
}
