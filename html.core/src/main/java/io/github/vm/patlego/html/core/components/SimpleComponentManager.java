package io.github.vm.patlego.html.core.components;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import io.github.vm.patlego.html.core.utils.GsonUtil;
import io.github.vm.patlego.html.core.utils.impl.ComponentGsonUtil;
import io.github.vm.patlego.html.datasource.repo.ComponentDS;

@Component(service = ComponentManager.class, immediate = true)
public class SimpleComponentManager implements ComponentManager {

    @Reference
    private ComponentDS componentDataSource;

    @Override
    public io.github.vm.patlego.html.datasource.tables.Component createComponent(InputStream in) throws IOException {

        Gson gson = new ComponentGsonUtil().getGson();

        io.github.vm.patlego.html.datasource.tables.Component component = gson.fromJson(new InputStreamReader(in),
                io.github.vm.patlego.html.datasource.tables.Component.class);

        component.setCreated(LocalDateTime.now());
        component.setUpdated(LocalDateTime.now());

        return this.componentDataSource.insertComponent(component);
    }

    @Override
    public io.github.vm.patlego.html.datasource.tables.Component updateComponent(InputStream in) throws IOException {
        Gson gson = new ComponentGsonUtil().getGson();

        io.github.vm.patlego.html.datasource.tables.Component component = gson.fromJson(new InputStreamReader(in),
                io.github.vm.patlego.html.datasource.tables.Component.class);

        if (component.getCreated() == null) {
            throw new IllegalArgumentException(
                    "Cannot update a component with a undefined created time. Please make sure to provide a valid creation time");
        }

        if (component.getUpdated() == null) {
            component.setUpdated(LocalDateTime.now());
        }

        if (component.getCreated().isAfter(component.getUpdated())) {
            throw new IllegalArgumentException("Cannot update a component with a created time that is after the update time");
        }

        return this.componentDataSource.updateComponent(component);
    }

}
