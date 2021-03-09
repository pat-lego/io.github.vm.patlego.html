package io.github.vm.patlego.html.datasource.repo;

import java.util.List;
import io.github.vm.patlego.html.datasource.tables.Component;

public interface ComponentDS {

    public List<Component> getComponents();

    public Component getComponent(Long id);

    public Component insertComponent(Component component);

    public Component deleteComponent(Long id);

    public Component updateComponent(Component component);
    
}
