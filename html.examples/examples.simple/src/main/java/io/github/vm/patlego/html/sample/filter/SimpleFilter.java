package io.github.vm.patlego.html.sample.filter;

import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jknack.handlebars.internal.lang3.StringUtils;
import com.github.jknack.handlebars.io.ServletContextTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;

import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.vm.patlego.html.filter.AbstractFilter;
import io.github.vm.patlego.html.parser.ParseableLoader;
import io.github.vm.patlego.html.parser.SimpleMustacheParser;
import io.github.vm.patlego.html.parser.impl.BundleContextParseableLoader;

public class SimpleFilter extends AbstractFilter {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String CONTEXT_PATH = "/html/examples/simple/";
    private final String HTML_EXTENSION = ".html";

    @Override
    public void template(HttpServletRequest request, HttpServletResponse response) {
       
        try {
            URL requestURL = new URL(request.getRequestURL().toString());
            String path = requestURL.getPath().replace(CONTEXT_PATH, StringUtils.EMPTY).replace(HTML_EXTENSION, StringUtils.EMPTY);

            if (path.equals(StringUtils.EMPTY)) {
                path = "index";
            }

            BundleContext context = (BundleContext) request.getServletContext().getAttribute("osgi-bundlecontext");
            
            ParseableLoader parseableLoader = new BundleContextParseableLoader(context);
            TemplateLoader loader = new ServletContextTemplateLoader(request.getServletContext(), "/", HTML_EXTENSION);
            
            String templatedResponse = SimpleMustacheParser.parse(path, loader, parseableLoader);

            PrintWriter responseWriter = response.getWriter();
            response.setContentLength(templatedResponse.length());
            responseWriter.write(templatedResponse);
            
        } catch (Exception e) {
            logger.error("Caught Exception ", e);
        }
    }

}
