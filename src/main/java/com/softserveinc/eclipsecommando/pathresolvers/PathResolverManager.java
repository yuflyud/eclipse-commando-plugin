package com.softserveinc.eclipsecommando.pathresolvers;

import java.util.ArrayList;
import java.util.List;

import com.softserveinc.eclipsecommando.pathresolvers.impl.FilePathResolver;
import com.softserveinc.eclipsecommando.pathresolvers.impl.ProjectPathResolver;
import com.softserveinc.eclipsecommando.pathresolvers.impl.ResourcePathResolver;
import com.softserveinc.eclipsecommando.pathresolvers.impl.java.JarPackageRootPathResolver;
import com.softserveinc.eclipsecommando.pathresolvers.impl.java.JavaElementPathResolver;
import com.softserveinc.eclipsecommando.pathresolvers.impl.java.JavaProjectPathResolver;
import com.softserveinc.eclipsecommando.pathresolvers.impl.java.PackagePathResolver;
import com.softserveinc.eclipsecommando.pathresolvers.impl.java.PackageRootPathResolver;

public class PathResolverManager {
    private static final List<IPathResolver> resolversList;

    static {
        resolversList = new ArrayList<IPathResolver>();
        resolversList.add(new FilePathResolver());
        resolversList.add(new ProjectPathResolver());
        resolversList.add(new ResourcePathResolver());
        // Java element resolvers
        resolversList.add(new JarPackageRootPathResolver());
        resolversList.add(new JavaProjectPathResolver());
        resolversList.add(new PackageRootPathResolver());
        resolversList.add(new PackagePathResolver());
        // IMPORTANT! JavaElementPathResolver must be the last java element
        // resolver because it's most general.
        resolversList.add(new JavaElementPathResolver());
    }

    private PathResolverManager() {
    }

    public static String resolvePath(Object object) throws ResolverNotFoundException {
        resolver: for (IPathResolver resolver : resolversList) {
            for (Class<?> clazz : resolver.getSourceClasses())
                if (!clazz.isInstance(object))
                    continue resolver;
            return resolver.resolvePath(object);
        }
        throw new ResolverNotFoundException(
                "No path could be retrieved for selected object. Current object type not supported.");
    }
}
