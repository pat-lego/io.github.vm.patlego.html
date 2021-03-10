package io.github.vm.patlego.html.core.components;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

import com.google.gson.Gson;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import io.github.vm.patlego.html.datasource.repo.ComponentDS;

@Component(service = ComponentManager.class, immediate = true)
public class SimpleComponentManager implements ComponentManager {

    @Reference
    private ComponentDS componentDataSource;

    @Override
    public io.github.vm.patlego.html.datasource.tables.Component createComponent(InputStream in) throws IOException {

        io.github.vm.patlego.html.datasource.tables.Component component = new Gson().fromJson(new InputStreamReader(in),
                io.github.vm.patlego.html.datasource.tables.Component.class);

        if (component.getCreated() == null) {
            component.setCreated(LocalDateTime.now());
        }
        
        if (component.getUpdated() == null) {
            component.setUpdated(LocalDateTime.now());
        }

        return this.componentDataSource.insertComponent(component);
    }

}
