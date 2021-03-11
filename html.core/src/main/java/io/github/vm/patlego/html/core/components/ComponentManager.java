package io.github.vm.patlego.html.core.components;

import java.io.InputStream;
import java.util.List;

import io.github.vm.patlego.html.core.components.exceptions.ComponentException;
import io.github.vm.patlego.html.datasource.tables.Component;

public interface ComponentManager {

    public Component createComponent(InputStream in) throws ComponentException;

    public Component updateComponent(InputStream in) throws ComponentException;

    public Component getComponent(Long id) throws ComponentException;

    public List<Component> getComponents() throws ComponentException;

    public Component deleteComponent(Long id) throws ComponentException;
    
}
