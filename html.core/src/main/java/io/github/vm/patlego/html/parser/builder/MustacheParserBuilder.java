package io.github.vm.patlego.html.parser.builder;

import java.util.ArrayList;
import java.util.List;

import com.github.jknack.handlebars.cache.TemplateCache;
import com.github.jknack.handlebars.io.TemplateLoader;

import io.github.vm.patlego.html.parser.ParseableLoader;

public class MustacheParserBuilder {

    private String template;
    private TemplateLoader templateLoader;
    private ParseableLoader parseableLoader;
    private TemplateCache cache;
    private List<Object> helpers;

    public final String getTemplate() {
        return this.template;
    }

    public final TemplateLoader getTemplateLoader() {
        return this.templateLoader;
    }
    
    public final ParseableLoader getParseableLoader() {
        return this.parseableLoader;
    }

    public final List<Object> getHelpers() {
        return this.helpers;
    }

    public final TemplateCache getCache() {
        return this.cache;
    }

    public static class Builder {
        private String template;
        private TemplateLoader templateLoader;
        private ParseableLoader parseableLoader;
        private TemplateCache cache;
        private List<Object> helpers = new ArrayList<>();
        
        public Builder setTemplate(String template) {
            this.template = template;
            return this;
        }

        public Builder setTemplatLoader(TemplateLoader templateLoader) {
            this.templateLoader = templateLoader;
            return this;
        }

        public Builder setParseableLoader(ParseableLoader parseableLoader) {
            this.parseableLoader = parseableLoader;
            return this;
        }

        public Builder setHelpers(Object helper) {
            helpers.add(helper);
            return this;
        }

        public Builder setCache(TemplateCache cache) {
            this.cache = cache;
            return this;
        }

        public MustacheParserBuilder build() {
            MustacheParserBuilder builder = new MustacheParserBuilder();
            builder.helpers = helpers;
            builder.parseableLoader = parseableLoader;
            builder.template = template;
            builder.templateLoader = templateLoader;
            builder.cache = cache;

            return builder;
        }
    }
    
}
