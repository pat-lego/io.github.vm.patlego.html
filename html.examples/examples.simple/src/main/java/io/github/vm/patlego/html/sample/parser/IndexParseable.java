package io.github.vm.patlego.html.sample.parser;

import org.osgi.service.component.annotations.Component;

import io.github.vm.patlego.html.Parseable;
import io.github.vm.patlego.html.enumerations.ParseableProperty;
import io.github.vm.patlego.html.sample.parser.header.Header;

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
        private Header header = new Header();

        public Header getHeader() {
            return header;
        }
    }
}
