package com.softserveinc.eclipsecommando.pathresolvers.impl.java;

import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;

import com.softserveinc.eclipsecommando.pathresolvers.IPathResolver;

public class JavaProjectPathResolver implements IPathResolver {

    @Override
    public String resolvePath(Object object) {
        IJavaProject prj = (IJavaProject) object;
        return prj.getProject().getLocation().toOSString();
    }

    @Override
    public Class<?>[] getSourceClasses() {
        return new Class<?>[] { IJavaElement.class, IJavaProject.class };
    }
}