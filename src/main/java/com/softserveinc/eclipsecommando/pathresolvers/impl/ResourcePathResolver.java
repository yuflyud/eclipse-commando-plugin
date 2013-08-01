package com.softserveinc.eclipsecommando.pathresolvers.impl;

import org.eclipse.core.resources.IResource;

import com.softserveinc.eclipsecommando.pathresolvers.IPathResolver;

public class ResourcePathResolver implements IPathResolver {

    @Override
    public String resolvePath(Object object) {
        IResource resource = (IResource) object;
        return resource.getLocation().toOSString();
    }

    @Override
    public Class<?>[] getSourceClasses() {
        return new Class<?>[] { IResource.class };
    }
}
