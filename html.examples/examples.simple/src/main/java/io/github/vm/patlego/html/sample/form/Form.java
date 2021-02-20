package io.github.vm.patlego.html.sample.form;

import java.util.List;

import io.github.vm.patlego.html.sample.form.constants.FormMethod;

public interface Form {

    public String id();

    public String action();

    public FormMethod method();

    public List<FormFields> fields();
    
}
