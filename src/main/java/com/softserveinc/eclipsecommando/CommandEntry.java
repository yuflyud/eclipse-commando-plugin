package com.softserveinc.eclipsecommando;

import java.io.File;

public final class CommandEntry {
    final String command;
    final File workDir;
    final Boolean applyToProject;
    final Boolean refresOnComplete;

    public CommandEntry(String command, File workDir, Boolean applyToProject,
            Boolean refresOnComplete) {
        super();
        this.command = command;
        this.workDir = workDir;
        this.applyToProject = applyToProject;
        this.refresOnComplete = refresOnComplete;
    }

    public String getCommand() {
        return command;
    }

    public File getWorkDir() {
        return workDir;
    }

    public Boolean getApplyToProject() {
        return applyToProject;
    }

    public Boolean getRefresOnComplete() {
        return refresOnComplete;
    }
}
