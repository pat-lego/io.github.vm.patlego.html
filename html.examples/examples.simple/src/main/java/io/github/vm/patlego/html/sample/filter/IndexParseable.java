package io.github.vm.patlego.html.sample.filter;

import org.osgi.service.component.annotations.Component;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String brother;

        public String getBrother() {
            return brother;
        }

        public void setBrother(String brother) {
            this.brother = brother;
        }
    }
}
