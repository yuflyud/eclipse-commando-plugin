package com.softserveinc.eclipsecommando.pathresolvers;

public interface IPathResolver {
    String resolvePath(Object object);
    Class<?>[] getSourceClasses();
}
