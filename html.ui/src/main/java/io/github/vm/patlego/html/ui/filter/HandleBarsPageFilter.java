package io.github.vm.patlego.html.ui.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.github.vm.patlego.html.core.filter.AbstractFilter;

import java.io.PrintWriter;
import java.net.URL;

import com.github.jknack.handlebars.cache.ConcurrentMapTemplateCache;
import com.github.jknack.handlebars.helper.ConditionalHelpers;
import com.github.jknack.handlebars.helper.StringHelpers;
import com.github.jknack.handlebars.internal.lang3.StringUtils;
import com.github.jknack.handlebars.io.ServletContextTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;

import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.vm.patlego.html.core.parser.ParseableLoader;
import io.github.vm.patlego.html.core.parser.SimpleMustacheParser;
import io.github.vm.patlego.html.core.parser.builder.MustacheParserBuilder;
import io.github.vm.patlego.html.core.parser.parseable.BundleContextParseableLoader;

public class HandleBarsPageFilter extends AbstractFilter {

    private static final String CONTEXT_PATH = "/html/ui/";
    private static final String HTML_EXTENSION = ".html";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void template(HttpServletRequest request, HttpServletResponse response) {
        try {
            
            URL requestURL = new URL(request.getRequestURL().toString());
            String path = requestURL.getPath().replace(CONTEXT_PATH, StringUtils.EMPTY);

            if (Boolean.FALSE.equals(isValid(path))) {
                return;
            }

            path = getValidUrlPath(path);

            BundleContext context = (BundleContext) request.getServletContext().getAttribute("osgi-bundlecontext");

            ParseableLoader parseableLoader = new BundleContextParseableLoader(context, CONTEXT_PATH);
            TemplateLoader loader = new ServletContextTemplateLoader(request.getServletContext(), "/", HTML_EXTENSION);

            MustacheParserBuilder builder = new MustacheParserBuilder.Builder()
                    .setCache(new ConcurrentMapTemplateCache()).setTemplate(path).setTemplatLoader(loader)
                    .setParseableLoader(parseableLoader).setHelpers(ConditionalHelpers.class)
                    .setHelpers(StringHelpers.class).build();
            String templatedResponse = SimpleMustacheParser.parse(builder);

            PrintWriter responseWriter = response.getWriter();
            response.setContentLength(templatedResponse.length());
            responseWriter.write(templatedResponse);

        } catch (Exception e) {
            logger.error("Caught an exception when filtering the page transformation ", e);
        }

    }

    public Boolean isValid(String path) {
        if (path.toLowerCase().endsWith("js") || path.toLowerCase().endsWith("css"))  {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    public String getValidUrlPath(String path) {

        if (path.contains(HTML_EXTENSION)) {
            return path.replace(HTML_EXTENSION, StringUtils.EMPTY);
        }

        if (path.isEmpty()) {
            return "index";
        }

        if (path.endsWith("/"))  {
            return String.format("%sindex", path);
        } else {
            return String.format("%s/index", path);
        }

    }

}
