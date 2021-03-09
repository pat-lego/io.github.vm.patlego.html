package io.github.vm.patlego.html.core.parser;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import io.github.vm.patlego.html.core.Parseable;
import io.github.vm.patlego.html.core.exceptions.ParseableLoaderException;

public interface ParseableLoader {

    public @Nullable Parseable getParseable(@Nonnull String template) throws ParseableLoaderException;
    
}
