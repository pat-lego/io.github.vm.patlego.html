package io.github.vm.patlego.html.parser.impl;

import java.util.Collection;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.vm.patlego.html.Parseable;
import io.github.vm.patlego.html.enumerations.ParseableProperty;
import io.github.vm.patlego.html.parser.ParseableLoader;

public class BundleContextParseableLoader implements ParseableLoader {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private BundleContext context;

    public BundleContextParseableLoader(BundleContext context) {
        this.context = context;
    }

    @Override
    public Parseable getParseable(String template) {
        try {
            Collection<ServiceReference<Parseable>> serviceReference = context.getServiceReferences(Parseable.class,
                    String.format(("(%s=%s)"), ParseableProperty.TEMPLATE, template));
            if (!serviceReference.isEmpty()) {
                return context.getService(serviceReference.iterator().next());
            }
        } catch (InvalidSyntaxException e) {
            logger.error("Caught an InvalidSyntaxException when attempting to get Parseable OSGi services", e);
        }

        return null;
    }

}
