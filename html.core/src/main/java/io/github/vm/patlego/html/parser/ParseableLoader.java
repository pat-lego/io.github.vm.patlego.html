package io.github.vm.patlego.html.parser;

import io.github.vm.patlego.html.Parseable;
import io.github.vm.patlego.html.exceptions.ParseableLoaderException;

public interface ParseableLoader {

    public Parseable getParseable(String template) throws ParseableLoaderException;
    
}
