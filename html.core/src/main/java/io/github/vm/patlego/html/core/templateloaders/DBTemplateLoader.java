package io.github.vm.patlego.html.core.templateloaders;

import java.io.IOException;
import java.nio.charset.Charset;

import com.github.jknack.handlebars.io.TemplateLoader;
import com.github.jknack.handlebars.io.TemplateSource;


public class DBTemplateLoader implements TemplateLoader {

    private String prefix;
    private String suffix;
    private Charset charset;
    
    @Override
    public TemplateSource sourceAt(String location) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String resolve(String location) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getPrefix() {
        return this.prefix;
    }

    @Override
    public String getSuffix() {
        return this.suffix;
    }

    @Override
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public void setCharset(Charset charset) {
       this.charset = charset;
    }

    @Override
    public Charset getCharset() {
       return this.charset;
    }
    
}
