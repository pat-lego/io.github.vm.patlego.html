package io.github.vm.patlego.html.core.pages.exceptions;

public class PageException extends Exception {
    
    /**
     *
     */
    private static final long serialVersionUID = 7117840906525674298L;

    public PageException(String msg, Throwable t) {
        super(msg, t);
    }

    public PageException(String msg) {
        super(msg);
    }

    public PageException(Throwable t) {
        super(t);
    }
    
}
