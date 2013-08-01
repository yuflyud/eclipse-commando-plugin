package com.softserveinc.eclipsecommando.presenter.impl;

import java.io.File;

import com.softserveinc.eclipsecommando.model.CommandEntry;
import com.softserveinc.eclipsecommando.presenter.ModelObject;

public class CommandEntryPresenter extends ModelObject {
    private String command;
    private File workDir;
    private Boolean applyToProject;
    private Boolean refresOnComplete;

    public CommandEntryPresenter() {
        super();
    }

    public CommandEntryPresenter(String command, File workDir, Boolean applyToProject,
            Boolean refresOnComplete) {
        this();
        this.command = command;
        this.workDir = workDir;
        this.applyToProject = applyToProject;
        this.refresOnComplete = refresOnComplete;
    }

    public CommandEntryPresenter(CommandEntry commandEntry) {
        this(commandEntry.getCommand(), commandEntry.getWorkDir(),
                commandEntry.getApplyToProject(), commandEntry.getRefresOnComplete());
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.firePropertyChange("command", this.command, this.command = command);
    }

    public File getWorkDir() {
        return workDir;
    }

    public void setWorkDir(File workDir) {
        this.firePropertyChange("workDir", this.workDir, this.workDir = workDir);
    }

    public Boolean getApplyToProject() {
        return applyToProject;
    }

    public void setApplyToProject(Boolean applyToProject) {
        this.firePropertyChange("applyToProject", this.applyToProject,
                this.applyToProject = applyToProject);
    }

    public Boolean getRefresOnComplete() {
        return refresOnComplete;
    }

    public void setRefresOnComplete(Boolean refresOnComplete) {
        this.firePropertyChange("refresOnComplete", this.refresOnComplete,
                this.refresOnComplete = refresOnComplete);
    }
}
