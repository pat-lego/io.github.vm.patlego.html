![Build Status](https://github.com/pat-lego/io.github.vm.patlego.html/workflows/Build%20and%20Test/badge.svg)
# io.github.vm.patlego.html
A templating engine for HTML markup in the Java web container.

# Use Case
Web development has always had the challenge of delivering content in a timely fashion with complex business rules. Many modern web frameworks such as Vue, React and Angular solve this issue but they require many web requests to be completed in order to get the data and merge it into the markup. This solution offers the ability to merge data into the markup prior to sending the document to the client browser. Once the document has been sent to the browser then we can let Javascript handle the user interaction of the document with the state already loaded into the document.

# Why this lib?
There are many libraries that offer server side templating capabilties, but they require many dependencies and other utilities to be present this library is old school. It only requires a few things:

  - Servlet Container
  - OSGi 
  - [Java Handlebars](https://github.com/jknack/handlebars.java)

With these 3 requirements present we can then integrate this library into the Java Servlet container to allow for server side HTML parsing.

# Code Sample

Here is a sample implementation of a filter that will perform the templating for us

**SimpleFilter.java**

```
import io.github.vm.patlego.html.filter.AbstractFilter;
import io.github.vm.patlego.html.parser.ParseableLoader;
import io.github.vm.patlego.html.parser.SimpleMustacheParser;
import io.github.vm.patlego.html.parser.impl.BundleContextParseableLoader;

public class SimpleFilter extends AbstractFilter {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String CONTEXT_PATH = "/patlego/";
    private final String HTML_EXTENSION = ".html";

    @Override
    public void template(HttpServletRequest request, HttpServletResponse response) {
       
        try {
            URL requestURL = new URL(request.getRequestURL().toString());
            String path = requestURL.getPath().replace(CONTEXT_PATH, StringUtils.EMPTY).replace(HTML_EXTENSION, StringUtils.EMPTY);

            BundleContext context = (BundleContext) request.getServletContext().getAttribute("osgi-bundlecontext");
            
            ParseableLoader parseableLoader = new BundleContextParseableLoader(context);
            TemplateLoader loader = new ServletContextTemplateLoader(request.getServletContext(), "/", HTML_EXTENSION);
            
            String templatedResponse = SimpleMustacheParser.parse(path, loader, parseableLoader);

            PrintWriter responseWriter = response.getWriter();
            response.setContentLength(templatedResponse.length());
            responseWriter.write(templatedResponse);
        } 
        ....
```

**web.xml**

```
<web-app xmlns="http://java.sun.com/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd" version="3.0">
    <display-name>Web Container Extender</display-name>

    <filter>
        <filter-name>simpleLogger</filter-name>
        <filter-class>io.github.vm.patlego.website.filter.SimpleFilter</filter-class>
    </filter>

    <filter-mapping>
        <filter-name>simpleLogger</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

</web-app>
```

**Parseable.java**

```
import io.github.vm.patlego.html.Parseable;
import io.github.vm.patlego.html.enumerations.ParseableProperty;

@Component(service = Parseable.class, immediate = true,
property = {
    ParseableProperty.TEMPLATE + "=index.html"
})
public class IndexParseable implements Parseable {

    @Override
    public Object bean() {
        Person pat = new Person();
        pat.setName("Pat");
        pat.setBrother("Serge & Justin");
        return pat;
    }
      
    private static class Person {
        private String name;
        private String brother;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBrother() {
            return brother;
        }

        public void setBrother(String brother) {
            this.brother = brother;
        }
    }
}
```

**index.html**

```
<html lang="en">
    <head>
        <title>Pat</title>
    </head>
    <body>
        {{>justin}}
        <h1>Serge</h1>
        <h1>{{name}}</h1>
    </body>
</html>
```

With this sample implementation the filter will find the instance of the index.html template and template it if a Parseable component exists within the OSGi container. Once it finds the Parseable instance for the template it will template it and return the result to the Output stream of the web request.

# How to build

Clone the repo and simply run `mvn clean install`

# Contributors

[Patrique Legault](https://github.com/pat-lego)