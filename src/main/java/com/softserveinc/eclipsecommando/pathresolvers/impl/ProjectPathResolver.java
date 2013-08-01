package com.softserveinc.eclipsecommando.pathresolvers.impl;

import org.eclipse.core.resources.IProject;

import com.softserveinc.eclipsecommando.pathresolvers.IPathResolver;

public class ProjectPathResolver implements IPathResolver {

    @Override
    public String resolvePath(Object object) {
        IProject prj = (IProject) object;
        return prj.getLocation().toOSString();
    }

    @Override
    public Class<?>[] getSourceClasses() {
        return new Class<?>[] { IProject.class };
    }
}
