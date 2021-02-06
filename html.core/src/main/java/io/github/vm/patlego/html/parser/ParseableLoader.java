package io.github.vm.patlego.html.parser;

import io.github.vm.patlego.html.Parseable;

public interface ParseableLoader {

    public Parseable getParseable(String template);
    
}
