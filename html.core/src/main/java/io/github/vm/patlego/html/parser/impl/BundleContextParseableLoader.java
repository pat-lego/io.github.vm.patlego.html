package io.github.vm.patlego.html.parser.impl;

import java.util.Collection;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import io.github.vm.patlego.html.Parseable;
import io.github.vm.patlego.html.enumerations.ParseableProperty;
import io.github.vm.patlego.html.exceptions.ParseableLoaderException;
import io.github.vm.patlego.html.parser.ParseableLoader;

public class BundleContextParseableLoader implements ParseableLoader {

    private BundleContext context;

    public BundleContextParseableLoader(BundleContext context) {
        this.context = context;
    }

    @Override
    public Parseable getParseable(String template) throws ParseableLoaderException {
        try {
            Collection<ServiceReference<Parseable>> serviceReference = context.getServiceReferences(Parseable.class,
                    String.format(("(%s=%s)"), ParseableProperty.TEMPLATE, template));
            if (!serviceReference.isEmpty()) {
                return context.getService(serviceReference.iterator().next());
            }
            else {
                throw new ParseableLoaderException(String.format("Cannot locate Parseable services in OSGi context with the %s property", ParseableProperty.TEMPLATE));
            }
        } catch (InvalidSyntaxException e) {
            throw new ParseableLoaderException(String.format("Cannot locate Parseable services in OSGi context please make sure that the %s property is in the @Component annotation", ParseableProperty.TEMPLATE), e);
        }
    }

}
