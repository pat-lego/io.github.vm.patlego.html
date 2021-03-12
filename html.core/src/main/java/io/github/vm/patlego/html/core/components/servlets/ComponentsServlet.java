package io.github.vm.patlego.html.core.components.servlets;

import java.io.IOException;
import java.util.List;

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

import io.github.vm.patlego.html.core.components.ComponentManager;
import io.github.vm.patlego.html.core.components.exceptions.ComponentException;
import io.github.vm.patlego.html.core.constants.MediaType;

@Component(service = Servlet.class, immediate = true, property = { "alias=/html/services/components",
        "servlet-name=HTML Services Components" })
public class ComponentsServlet extends HttpServlet implements Servlet {

    /**
     *
     */
    private static final long serialVersionUID = -416021442433251450L;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static String id = "id";

    private static String ioException = "Failed to create the component";
    private static String jsonIOException = "Failed to convert the component(s) to a String object";
    private static String componentException = "Failed to retrieve the component from the compoent manager";
    private static String numberFormatException = "Failed to convert the submited component ID to a Number";

    @Reference
    private ComponentManager componentManager;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            io.github.vm.patlego.html.datasource.components.tables.Component component = this.componentManager
                    .createComponent(req.getInputStream());

            new Gson().toJson(component, resp.getWriter());

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType(MediaType.JSON);

            resp.getWriter().flush();
            resp.getWriter().close();
        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error(ioException, e);
        } catch (JsonIOException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error(jsonIOException, e);
        } catch (ComponentException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error(componentException, e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        try {
            io.github.vm.patlego.html.datasource.components.tables.Component component = this.componentManager
                    .updateComponent(req.getInputStream());

            new Gson().toJson(component, resp.getWriter());

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType(MediaType.JSON);

            resp.getWriter().flush();
            resp.getWriter().close();
        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error(ioException, e);
        } catch (JsonIOException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error(jsonIOException, e);
        } catch (ComponentException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error(componentException, e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (req.getParameter(id) == null || req.getParameter(id).isEmpty()) {
                List<io.github.vm.patlego.html.datasource.components.tables.Component> components = this.componentManager
                        .getComponents();
                new Gson().toJson(components, resp.getWriter());
            } else {
                io.github.vm.patlego.html.datasource.components.tables.Component component = this.componentManager.getComponent(Long.parseLong(req.getParameter(ComponentsServlet.id)));
                new Gson().toJson(component, resp.getWriter());
            }

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType(MediaType.JSON);

            resp.getWriter().flush();
            resp.getWriter().close();

        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error(ioException, e);
        } catch (JsonIOException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error(jsonIOException, e);
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error(numberFormatException, e);
        } catch (ComponentException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error(componentException, e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (req.getParameter(id) == null || req.getParameter(id).isEmpty()) {
                logger.warn("Must supply an ID in order to invoke the delete operation of a component");
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return;
            } else {
                io.github.vm.patlego.html.datasource.components.tables.Component component = this.componentManager.deleteComponent(Long.parseLong(req.getParameter(id)));
                new Gson().toJson(component, resp.getWriter());
            }

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType(MediaType.JSON);

            resp.getWriter().flush();
            resp.getWriter().close();

        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error(ioException, e);
        } catch (JsonIOException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error(jsonIOException, e);
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error(numberFormatException, e);
        } catch (ComponentException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error(componentException, e);
        }
    }

}
