package io.github.vm.patlego.html.sample.parser;

import java.util.ArrayList;
import java.util.List;

import com.github.jknack.handlebars.internal.lang3.StringUtils;

import org.osgi.service.component.annotations.Component;

import io.github.vm.patlego.html.Parseable;
import io.github.vm.patlego.html.constants.ParseableProperty;
import io.github.vm.patlego.html.sample.form.Button;
import io.github.vm.patlego.html.sample.form.Form;
import io.github.vm.patlego.html.sample.form.FormFields;
import io.github.vm.patlego.html.sample.form.constants.ButtonType;
import io.github.vm.patlego.html.sample.form.constants.FormField;
import io.github.vm.patlego.html.sample.form.constants.FormMethod;
import io.github.vm.patlego.html.sample.parser.page.Head;
import io.github.vm.patlego.html.sample.parser.page.Header;

@Component(service = Parseable.class, immediate = true, property = { ParseableProperty.TEMPLATE + "=index.html" })
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

        public Content getContent() {
            return new Content();
        }
    }

    private static class Content {

        public Form getForm() {
            return new Form() {

                @Override
                public String getId() {
                    return "html-form";
                }

                @Override
                public String getAction() {
                    return StringUtils.EMPTY;
                }

                @Override
                public FormMethod getMethod() {
                    return FormMethod.POST;
                }

                @Override
                public Button getButton() {
                    return new Button() {

                        @Override
                        public String getSubject() {
                            return "Submit";
                        }

                        @Override
                        public String getId() {
                            return "submit";
                        }

                        @Override
                        public ButtonType getType() {
                            return ButtonType.SUBMIT;
                        }
                        
                    };
                }

                @Override
                public List<FormFields> getFields() {
                    FormFields firstName = new FormFields(){

                        @Override
                        public String getId() {
                           return "firstname";
                        }

                        @Override
                        public Boolean getMandatory() {
                           return Boolean.TRUE;
                        }

                        @Override
                        public FormField getFieldType() {
                            return FormField.TEXT;
                        }

                        @Override
                        public Object getValue() {
                            return StringUtils.EMPTY;
                        }

                        @Override
                        public String getLabel() {
                            return "First Name";
                        }

                        @Override
                        public String getName() {
                            return "firstname";
                        }
                    };

                    FormFields lastName = new FormFields(){

                        @Override
                        public String getId() {
                           return "lastname";
                        }

                        @Override
                        public Boolean getMandatory() {
                           return Boolean.FALSE;
                        }

                        @Override
                        public FormField getFieldType() {
                            return FormField.TEXT;
                        }

                        @Override
                        public Object getValue() {
                            return StringUtils.EMPTY;
                        }

                        @Override
                        public String getLabel() {
                            return "Last Name";
                        }

                        @Override
                        public String getName() {
                            return "lastname";
                        }
                    };

                    FormFields email = new FormFields(){

                        @Override
                        public String getId() {
                           return "email";
                        }

                        @Override
                        public Boolean getMandatory() {
                           return Boolean.TRUE;
                        }

                        @Override
                        public FormField getFieldType() {
                            return FormField.EMAIL;
                        }

                        @Override
                        public Object getValue() {
                            return StringUtils.EMPTY;
                        }

                        @Override
                        public String getLabel() {
                            return "Email";
                        }

                        @Override
                        public String getName() {
                            return "email";
                        }
                    };

                    FormFields age = new FormFields(){

                        @Override
                        public String getId() {
                           return "age";
                        }

                        @Override
                        public Boolean getMandatory() {
                           return Boolean.FALSE;
                        }

                        @Override
                        public FormField getFieldType() {
                            return FormField.NUMBER;
                        }

                        @Override
                        public Object getValue() {
                            return StringUtils.EMPTY;
                        }

                        @Override
                        public String getLabel() {
                            return "Age";
                        }

                        @Override
                        public String getName() {
                            return "age";
                        }
                    };

                    FormFields male = new FormFields(){

                        @Override
                        public String getId() {
                           return "male";
                        }

                        @Override
                        public Boolean getMandatory() {
                           return Boolean.FALSE;
                        }

                        @Override
                        public FormField getFieldType() {
                            return FormField.RADIO;
                        }

                        @Override
                        public Object getValue() {
                            return "male";
                        }

                        @Override
                        public String getLabel() {
                            return "Male";
                        }

                        @Override
                        public String getName() {
                            return "gender";
                        }
                    };

                    FormFields female = new FormFields(){

                        @Override
                        public String getId() {
                           return "female";
                        }

                        @Override
                        public Boolean getMandatory() {
                           return Boolean.FALSE;
                        }

                        @Override
                        public FormField getFieldType() {
                            return FormField.RADIO;
                        }

                        @Override
                        public Object getValue() {
                            return "female";
                        }

                        @Override
                        public String getLabel() {
                            return "Female";
                        }

                        @Override
                        public String getName() {
                            return "gender";
                        }
                    };

                    FormFields other = new FormFields(){

                        @Override
                        public String getId() {
                           return "other";
                        }

                        @Override
                        public Boolean getMandatory() {
                           return Boolean.FALSE;
                        }

                        @Override
                        public FormField getFieldType() {
                            return FormField.RADIO;
                        }

                        @Override
                        public Object getValue() {
                            return "other";
                        }

                        @Override
                        public String getLabel() {
                            return "Other";
                        }

                        @Override
                        public String getName() {
                            return "gender";
                        }
                    };

                    FormFields newsletter = new FormFields(){

                        @Override
                        public String getId() {
                           return "newletter";
                        }

                        @Override
                        public Boolean getMandatory() {
                           return Boolean.FALSE;
                        }

                        @Override
                        public FormField getFieldType() {
                            return FormField.CHECKBOX;
                        }

                        @Override
                        public Object getValue() {
                            return "agree";
                        }

                        @Override
                        public String getLabel() {
                            return "Concent to receiving news letters";
                        }

                        @Override
                        public String getName() {
                            return "newsletter";
                        }
                    };
                    
                    
                    return new ArrayList<FormFields>() {{
                        add(firstName);
                        add(lastName);
                        add(email);
                        add(age);
                        add(male);
                        add(female);
                        add(other);
                        add(newsletter);
                    }};
                }

            };
        }

    }
}
