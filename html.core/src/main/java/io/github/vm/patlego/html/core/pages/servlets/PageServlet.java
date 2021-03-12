package io.github.vm.patlego.html.core.pages.servlets;

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

import io.github.vm.patlego.html.core.constants.MediaType;
import io.github.vm.patlego.html.core.pages.PageManager;
import io.github.vm.patlego.html.core.pages.exceptions.PageException;
import io.github.vm.patlego.html.datasource.pages.tables.Page;

@Component(service = Servlet.class, immediate = true, property = { "alias=/html/services/pages",
        "servlet-name=HTML Services Pages" })
public class PageServlet extends HttpServlet implements Servlet {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static String id = "id";

    private static String ioException = "Unable to write the result of the page operation to the servlet stream";
    private static String pageException = "Unable to perform the necessary page operation";
    private static String jsonIOException = "Unable to serialize the Page object to json";

    private static String missingManadatoryParameter = "Missing mandatory parameter id in order to perform the desired page operation";

    @Reference
    private PageManager pageManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (req.getParameter(id) == null || req.getParameter(id).isEmpty()) {
                List<Page> pages = this.pageManager.getPages();
                new Gson().toJson(pages, resp.getWriter());
            } else {
                Page page = this.pageManager.getPage(req.getParameter(id));
                new Gson().toJson(page, resp.getWriter());
            }

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType(MediaType.JSON);

            resp.getWriter().flush();
            resp.getWriter().close();

        } catch (PageException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error(pageException, e);
        } catch (JsonIOException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error(jsonIOException, e);
        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error(ioException, e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Page page = this.pageManager.updatePage(req.getInputStream());
            new Gson().toJson(page, resp.getWriter());

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType(MediaType.JSON);

            resp.getWriter().flush();
            resp.getWriter().close();

        } catch (PageException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error(pageException, e);
        } catch (JsonIOException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error(jsonIOException, e);
        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error(ioException, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Page page = this.pageManager.createPage(req.getInputStream());
            new Gson().toJson(page, resp.getWriter());

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType(MediaType.JSON);

            resp.getWriter().flush();
            resp.getWriter().close();

        } catch (PageException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error(pageException, e);
        } catch (JsonIOException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error(jsonIOException, e);
        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error(ioException, e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (req.getParameter(id) == null || req.getParameter(id).isEmpty()) {
                logger.error(missingManadatoryParameter);
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return;
            }

            Page page = this.pageManager.deletePage(req.getParameter(id));
            new Gson().toJson(page, resp.getWriter());

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType(MediaType.JSON);

            resp.getWriter().flush();
            resp.getWriter().close();

        } catch (PageException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error(pageException, e);
        } catch (JsonIOException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error(jsonIOException, e);
        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            logger.error(ioException, e);
        }
    }

}
