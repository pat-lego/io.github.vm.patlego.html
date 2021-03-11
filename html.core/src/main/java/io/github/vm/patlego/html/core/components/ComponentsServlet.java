package io.github.vm.patlego.html.core.components;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.vm.patlego.html.core.constants.MediaType;

@Component(service = Servlet.class, immediate = true, property = { "alias=/html/services/components", "servlet-name=Services-Components"})
public class ComponentsServlet extends HttpServlet implements Servlet {

    /**
     *
     */
    private static final long serialVersionUID = -416021442433251450L;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Reference
    private ComponentManager componentManager;
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            io.github.vm.patlego.html.datasource.tables.Component component = this.componentManager.createComponent(req.getInputStream());
            
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType(MediaType.JSON);

            new Gson().toJson(component, resp.getWriter());
            resp.getWriter().flush();
            resp.getWriter().close();
        } catch (IOException e) {
            logger.error("Failed to create the component", e);
        } catch (JsonIOException e) {
            logger.error("Failed to serialize component object to Json", e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        try {
            io.github.vm.patlego.html.datasource.tables.Component component = this.componentManager.updateComponent(req.getInputStream());
            
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType(MediaType.JSON);

            new Gson().toJson(component, resp.getWriter());
            resp.getWriter().flush();
            resp.getWriter().close();
        } catch (IOException e) {
            logger.error("Failed to create the component", e);
        } catch (JsonIOException e) {
            logger.error("Failed to serialize component object to Json", e);
        }
    }

}
