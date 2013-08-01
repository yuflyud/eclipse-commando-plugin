package com.softserveinc.eclipsecommando.pathresolvers.impl.java;

import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IPackageFragment;

import com.softserveinc.eclipsecommando.pathresolvers.IPathResolver;

public class PackagePathResolver implements IPathResolver {

    @Override
    public String resolvePath(Object object) {
        IResource resource = ((IPackageFragment) object).getResource();
        return resource.getLocation().toOSString();
    }

    @Override
    public Class<?>[] getSourceClasses() {
        return new Class<?>[] { IJavaElement.class, IPackageFragment.class };
    }
}