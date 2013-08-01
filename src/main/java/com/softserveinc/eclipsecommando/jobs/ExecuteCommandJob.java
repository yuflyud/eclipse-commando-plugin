package com.softserveinc.eclipsecommando.jobs;

import java.io.IOException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import com.softserveinc.eclipsecommando.activator.Activator;
import com.softserveinc.eclipsecommando.model.CommandEntry;
import com.softserveinc.eclipsecommando.utils.ConsoleUtil;

public class ExecuteCommandJob extends Job {
    private final CommandEntry commandEntry;

    public ExecuteCommandJob(String jobName, CommandEntry commandEntry) {
        super(jobName);
        this.commandEntry = commandEntry;
    }

    @Override
    protected IStatus run(IProgressMonitor monitor) {
        if (monitor == null)
            monitor = new NullProgressMonitor();
        try {
            // TODO improve task names
            monitor.beginTask(this.getName(), IProgressMonitor.UNKNOWN);

            ProcessBuilder builder = new ProcessBuilder("cmd", "/c", commandEntry.getCommand());
            builder.directory(commandEntry.getWorkDir());
            builder.redirectErrorStream(true);
            Process process = builder.start();

            ConsoleUtil.writeProcessOutputToConsole(process, "Commando Console");

        } catch (IOException e) {
            // TODO Log message
            return new Status(Status.ERROR, Activator.PLUGIN_ID, Status.ERROR,
                    e.getLocalizedMessage(), e);
        } finally {
            monitor.done();
        }

        return Status.OK_STATUS;
    }

}
