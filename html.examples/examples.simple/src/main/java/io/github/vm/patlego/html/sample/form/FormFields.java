package io.github.vm.patlego.html.sample.form;

import io.github.vm.patlego.html.sample.form.constants.FormField;

public interface FormFields {

    public String getId();

    public Boolean getIsMandatory();

    public FormField getFieldType();

    public Object getValue();

    public String getLabel();

    public String getName();
    
}
