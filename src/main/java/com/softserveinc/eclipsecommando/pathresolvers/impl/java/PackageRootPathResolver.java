package com.softserveinc.eclipsecommando.pathresolvers.impl.java;

import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IPackageFragmentRoot;

import com.softserveinc.eclipsecommando.pathresolvers.IPathResolver;

public class PackageRootPathResolver implements IPathResolver {

    @Override
    public String resolvePath(Object object) {
        IPackageFragmentRoot root = (IPackageFragmentRoot) object;
        String path = root.getJavaProject().getProject().getParent().getLocation().toOSString();
        return path + root.getPath().toOSString();
    }

    @Override
    public Class<?>[] getSourceClasses() {
        return new Class<?>[] { IJavaElement.class, IPackageFragmentRoot.class };
    }
}