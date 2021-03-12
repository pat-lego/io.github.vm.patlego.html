package io.github.vm.patlego.html.datasource.components;

import java.util.List;
import io.github.vm.patlego.html.datasource.components.tables.Component;

public interface ComponentDS {

    public List<Component> getComponents();

    public Component getComponent(Long id);

    public Component insertComponent(Component component);

    public Component deleteComponent(Long id);

    public Component updateComponent(Component component);
    
}
