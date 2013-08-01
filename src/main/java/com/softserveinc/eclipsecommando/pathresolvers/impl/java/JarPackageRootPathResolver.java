package com.softserveinc.eclipsecommando.pathresolvers.impl.java;

import java.io.File;

import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.internal.core.JarPackageFragmentRoot;

import com.softserveinc.eclipsecommando.pathresolvers.IPathResolver;

@SuppressWarnings("restriction")
public class JarPackageRootPathResolver implements IPathResolver {

    @Override
    public String resolvePath(Object object) {
        IPackageFragmentRoot root = (IPackageFragmentRoot) object;
        String path = root.getPath().toOSString();
        return path.substring(0, path.lastIndexOf(File.separator));
    }

    @Override
    public Class<?>[] getSourceClasses() {
        return new Class<?>[] { IJavaElement.class, JarPackageFragmentRoot.class };
    }
}