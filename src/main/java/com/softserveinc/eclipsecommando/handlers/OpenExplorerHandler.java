package com.softserveinc.eclipsecommando.handlers;

import java.io.File;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import com.softserveinc.eclipsecommando.CommandEntry;
import com.softserveinc.eclipsecommando.jobs.ExecuteCommandJob;
import com.softserveinc.eclipsecommando.pathresolvers.PathResolverManager;
import com.softserveinc.eclipsecommando.pathresolvers.ResolverNotFoundException;

public class OpenExplorerHandler extends AbstractHandler {
    public Object execute(ExecutionEvent event) throws ExecutionException {
        IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
        ISelection sel = window.getSelectionService().getSelection();
        if (!(sel instanceof IStructuredSelection))
            return null;

        Object obj = ((IStructuredSelection) sel).getFirstElement();
        String path;

        try {
            path = PathResolverManager.resolvePath(obj);
        } catch (ResolverNotFoundException e) {
            MessageDialog.openError(window.getShell(), "Commando Error", e.getLocalizedMessage());
            return null;
        }
        final String command = "mvn exec:exec";

        CommandEntry commandEntry = new CommandEntry(command, new File(path), false, false);

        Job job = new ExecuteCommandJob("Running: " + command, commandEntry);
        job.setPriority(Job.SHORT);
        job.schedule();
        return null;
    }
}