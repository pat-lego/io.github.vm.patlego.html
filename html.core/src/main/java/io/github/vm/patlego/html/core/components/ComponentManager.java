package io.github.vm.patlego.html.core.components;

import java.io.IOException;
import java.io.InputStream;

import io.github.vm.patlego.html.datasource.tables.Component;

public interface ComponentManager {

    public Component createComponent(InputStream in) throws IOException;

    public Component updateComponent(InputStream in) throws IOException;
    
}
