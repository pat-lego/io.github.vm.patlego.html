package io.github.vm.patlego.html.sample.form;

import java.util.List;

import io.github.vm.patlego.html.sample.form.constants.FormMethod;

public interface Form {

    public String getId();

    public String getAction();

    public FormMethod getMethod();

    public List<FormFields> getFields();
    
}
