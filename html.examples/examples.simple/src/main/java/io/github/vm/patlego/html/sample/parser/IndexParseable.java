package io.github.vm.patlego.html.sample.parser;

import org.osgi.service.component.annotations.Component;

import io.github.vm.patlego.html.Parseable;
import io.github.vm.patlego.html.constants.ParseableProperty;
import io.github.vm.patlego.html.sample.parser.page.Head;
import io.github.vm.patlego.html.sample.parser.page.Header;

@Component(service = Parseable.class, immediate = true,
property = {
    ParseableProperty.TEMPLATE + "=index.html"
})
public class IndexParseable implements Parseable {

    @Override
    public Object bean() {
        return new IndexPage();
    }
      
    private static class IndexPage {
        public Head getHead() {
            return new Head();
        }

        public Header getHeader() {
            return new Header();
        }
    }
}
