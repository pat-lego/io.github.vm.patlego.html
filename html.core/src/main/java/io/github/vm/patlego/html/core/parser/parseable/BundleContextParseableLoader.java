package io.github.vm.patlego.html.core.parser.parseable;

import java.util.Collection;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import io.github.vm.patlego.html.core.Parseable;
import io.github.vm.patlego.html.core.constants.ParseableProperty;
import io.github.vm.patlego.html.core.parser.exceptions.ParseableLoaderException;
import io.github.vm.patlego.html.core.parser.ParseableLoader;

public class BundleContextParseableLoader implements ParseableLoader {

    private BundleContext context;
    private String urlPrefix;

    public BundleContextParseableLoader(BundleContext context, String urlPrefix) {
        this.context = context;

        if (urlPrefix != null && !urlPrefix.isEmpty()) {
            if (!urlPrefix.endsWith("/")) {
                urlPrefix = String.format("%s/", urlPrefix);
            }
            this.urlPrefix = urlPrefix;
        }
    }

    @Override
    public Parseable getParseable(String template) throws ParseableLoaderException {
        try {
            if (this.urlPrefix != null) {
                template = String.format("%s%s", urlPrefix, template);
            }
            
            Collection<ServiceReference<Parseable>> serviceReference = context.getServiceReferences(Parseable.class,
                    String.format(("(%s=%s)"), ParseableProperty.TEMPLATE, template));
            if (!serviceReference.isEmpty()) {
                return context.getService(serviceReference.stream().findFirst().get());
            }
            else {
                return () -> {
                    return null;
                };
            }
        } catch (InvalidSyntaxException e) {
            throw new ParseableLoaderException(String.format("Cannot locate Parseable services in OSGi context please make sure that the %s property is in the @Component annotation", ParseableProperty.TEMPLATE), e);
        }
    }

}
