package com.softserveinc.eclipsecommando.pathresolvers.impl.java;

import java.io.File;

import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.IJavaElement;

import com.softserveinc.eclipsecommando.pathresolvers.IPathResolver;

public class JavaElementPathResolver implements IPathResolver {

    @Override
    public String resolvePath(Object object) {
        IResource resource = ((IJavaElement) object).getResource();
        String path = resource.getLocation().toOSString();
        return path.substring(0, path.lastIndexOf(File.separator));
    }

    @Override
    public Class<?>[] getSourceClasses() {
        return new Class<?>[] { IJavaElement.class };
    }
}