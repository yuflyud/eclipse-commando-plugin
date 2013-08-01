package com.softserveinc.eclipsecommando.pathresolvers.impl;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;

import com.softserveinc.eclipsecommando.pathresolvers.IPathResolver;

public class FilePathResolver implements IPathResolver {

    @Override
    public String resolvePath(Object object) {
        IResource resource = (IResource) object;
        String path = resource.getLocation().toOSString();
        path = path.substring(0, path.lastIndexOf(File.separator));
        return path;
    }

    @Override
    public Class<?>[] getSourceClasses() {
        return new Class<?>[] { IFile.class };
    }
}
