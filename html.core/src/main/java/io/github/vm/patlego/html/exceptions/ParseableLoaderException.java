package io.github.vm.patlego.html.exceptions;

public class ParseableLoaderException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = -3290928475337561986L;

    public ParseableLoaderException(String msg, Throwable t) {
        super(msg,t);
    }

    public ParseableLoaderException(String msg) {
        super(msg);
    }
    
}
