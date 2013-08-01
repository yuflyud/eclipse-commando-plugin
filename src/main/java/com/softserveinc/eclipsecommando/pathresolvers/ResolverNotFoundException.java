package com.softserveinc.eclipsecommando.pathresolvers;

public class ResolverNotFoundException extends Exception {
    private static final long serialVersionUID = 1883345783088344807L;

    public ResolverNotFoundException() {
        super();
    }

    public ResolverNotFoundException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public ResolverNotFoundException(String arg0) {
        super(arg0);
    }

    public ResolverNotFoundException(Throwable arg0) {
        super(arg0);
    }

}
