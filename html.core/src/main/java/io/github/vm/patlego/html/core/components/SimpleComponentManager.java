package io.github.vm.patlego.html.core.components;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;

import org.apache.commons.io.IOUtils;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import io.github.vm.patlego.html.datasource.repo.ComponentDS;

@Component(service = ComponentManager.class, immediate = true)
public class SimpleComponentManager implements ComponentManager {

    @Reference
    private ComponentDS componentDataSource;

    @Override
    public io.github.vm.patlego.html.datasource.tables.Component createComponent(InputStream in) throws IOException {
        String stream = IOUtils.toString(in, StandardCharsets.UTF_8);
        io.github.vm.patlego.html.datasource.tables.Component component = new Gson().fromJson(stream, io.github.vm.patlego.html.datasource.tables.Component.class);
        return this.componentDataSource.insertComponent(component);
    }
    
}
