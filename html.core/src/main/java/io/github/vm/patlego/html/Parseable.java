package io.github.vm.patlego.html;

import javax.annotation.Nonnull;

public interface Parseable {
    
    /**
     * Function used to get the data that will be applied to the template
     */
    public @Nonnull Object bean();

}
