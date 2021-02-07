package io.github.vm.patlego.html.parser;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import io.github.vm.patlego.html.Parseable;
import io.github.vm.patlego.html.exceptions.ParseableLoaderException;

public interface ParseableLoader {

    public @Nullable Parseable getParseable(@Nonnull String template) throws ParseableLoaderException;
    
}
