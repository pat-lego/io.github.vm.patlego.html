package io.github.vm.patlego.html.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("The filter has successfully been initialized by the servlet container");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        template((HttpServletRequest) request, (HttpServletResponse) response);
        chain.doFilter(request, response);

    }

    public abstract void template(HttpServletRequest request, HttpServletResponse response);

    @Override
    public void destroy() {
        logger.info("The filter has successfully been destroyed by the servlet container");
    }

}
