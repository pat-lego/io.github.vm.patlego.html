package io.github.vm.patlego.html.sample.parser.page;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Head {

    public String getTitle() {
        return "Simple HTML Core Page";
    }
    
    public Boolean getCacheControl() {
        return Boolean.TRUE;
    }

    public Set<Entry<String, String>> getMetadata() {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("description", "Simple Web Page built using Apache Karaf and Server Side Templating");
        metadata.put("keywords", "HTML CSS TailWind Vue.js Apache Karaf Handlebars");
        metadata.put("author", "Patrique Legault");
        return metadata.entrySet();
    }
}
