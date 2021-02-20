package io.github.vm.patlego.html.sample.form;

public interface FormFields {

    public String id();

    public Boolean isMandatory();

    public FormFields fieldType();

    public Object value();
    
}
