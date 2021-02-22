package io.github.vm.patlego.html.sample.submit;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

import io.github.vm.patlego.html.sample.submit.constants.Submit;

@Component(
    property = { "alias=" + Submit.SUBMIT_URL, "servlet-name=Example"}
)
public class SubmitHandler extends HttpServlet implements Servlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/plain");
        resp.getWriter().write("Thank you for your submission");
        resp.getWriter().flush();
        resp.getWriter().close();
    }
    
}
