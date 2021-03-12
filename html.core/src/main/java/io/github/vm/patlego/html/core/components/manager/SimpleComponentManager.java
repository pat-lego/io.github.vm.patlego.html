package io.github.vm.patlego.html.core.components.manager;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.List;

import com.google.gson.Gson;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import io.github.vm.patlego.html.core.components.ComponentManager;
import io.github.vm.patlego.html.core.components.exceptions.ComponentException;
import io.github.vm.patlego.html.core.components.utils.ComponentGsonUtil;
import io.github.vm.patlego.html.datasource.components.ComponentDS;

@Component(service = ComponentManager.class, immediate = true)
public class SimpleComponentManager implements ComponentManager {

    @Reference
    private ComponentDS componentDataSource;

    @Override
    public io.github.vm.patlego.html.datasource.components.tables.Component createComponent(InputStream in)
            throws ComponentException {

        try {
            Gson gson = new ComponentGsonUtil().getGson();

            io.github.vm.patlego.html.datasource.components.tables.Component component = gson.fromJson(new InputStreamReader(in),
                    io.github.vm.patlego.html.datasource.components.tables.Component.class);

            component.setCreated(LocalDateTime.now());
            component.setUpdated(LocalDateTime.now());

            return this.componentDataSource.insertComponent(component);
        } catch (Exception e) {
            throw new ComponentException(e);
        }
    }

    @Override
    public io.github.vm.patlego.html.datasource.components.tables.Component updateComponent(InputStream in)
            throws ComponentException {
        try {
            Gson gson = new ComponentGsonUtil().getGson();

            io.github.vm.patlego.html.datasource.components.tables.Component component = gson.fromJson(new InputStreamReader(in),
                    io.github.vm.patlego.html.datasource.components.tables.Component.class);

            if (component.getCreated() == null) {
                throw new IllegalArgumentException(
                        "Cannot update a component with a undefined created time. Please make sure to provide a valid creation time");
            }

            if (component.getUpdated() == null) {
                component.setUpdated(LocalDateTime.now());
            }

            if (component.getCreated().isAfter(component.getUpdated())) {
                throw new IllegalArgumentException(
                        "Cannot update a component with a created time that is after the update time");
            }

            return this.componentDataSource.updateComponent(component);
        } catch (Exception e) {
            throw new ComponentException(e);
        }
    }

    @Override
    public io.github.vm.patlego.html.datasource.components.tables.Component getComponent(Long id) throws ComponentException {
        try {
            if (id == null || id <= 0) {
                throw new IllegalArgumentException("Cannot query for a component with a null or negative integer");
            }

            return this.componentDataSource.getComponent(id);
        } catch (Exception e) {
            throw new ComponentException(e);
        }

    }

    @Override
    public List<io.github.vm.patlego.html.datasource.components.tables.Component> getComponents() throws ComponentException {
        try {
            return this.componentDataSource.getComponents();
        } catch (Exception e) {
            throw new ComponentException(e);
        }

    }

    @Override
    public io.github.vm.patlego.html.datasource.components.tables.Component deleteComponent(Long id) throws ComponentException {
        try {
            if (id == null || id <= 0) {
                throw new IllegalArgumentException("Cannot delete a component with a negative or null ID");
            }

            return this.componentDataSource.deleteComponent(id);
        } catch (Exception e) {
            throw new ComponentException(e);
        }

    }

}
