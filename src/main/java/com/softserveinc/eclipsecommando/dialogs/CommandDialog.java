package com.softserveinc.eclipsecommando.dialogs;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.softserveinc.eclipsecommando.model.CommandEntry;
import com.softserveinc.eclipsecommando.presenter.impl.CommandEntryPresenter;

public class CommandDialog extends TrayDialog {

    private Text commandText;
    private String windowTitle;
    private DataBindingContext bindingContext;
    private CommandEntryPresenter commandEntryPresenter;

    public CommandDialog(Shell parentShell) {
        super(parentShell);
        this.commandEntryPresenter = new CommandEntryPresenter();
    }

    public CommandDialog(Shell parentShell, CommandEntry commandEntry) {
        super(parentShell);
        this.commandEntryPresenter = new CommandEntryPresenter(commandEntry);
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);

        composite.setLayout(new GridLayout());
        composite.setLayoutData(new GridData(GridData.FILL_BOTH));

        // command group
        Group grpCommand = new Group(composite, SWT.NONE);
        grpCommand.setText("Command");
        grpCommand.setLayout(new GridLayout(1, false));
        grpCommand.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        // command text
        commandText = new Text(grpCommand, SWT.BORDER);
        // commandText.setFont(SWTResourceManager.getFont("Consolas", 9,
        // SWT.NORMAL));
        commandText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        commandText.setTextLimit(50);

        // additional group
        Group grpAdditionalActions = new Group(composite, SWT.NONE);
        grpAdditionalActions.setText("Additional actions");
        grpAdditionalActions.setLayout(new GridLayout(1, false));
        grpAdditionalActions.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));

        Button useProjectLocBut = new Button(grpAdditionalActions, SWT.CHECK);
        useProjectLocBut.setText("Apply to selected project location");

        Button refreshOnCompleteBut = new Button(grpAdditionalActions, SWT.CHECK);
        refreshOnCompleteBut.setText("Refresh resources upon completion");

        // TODO enable when implemented
        useProjectLocBut.setEnabled(false);
        refreshOnCompleteBut.setEnabled(false);

        bindingContext = initDataBindings();
        return parent;
    }

    private DataBindingContext initDataBindings() {
        DataBindingContext bindingContext = new DataBindingContext();

        IObservableValue nameTextObserveWidget = SWTObservables.observeText(commandText,
                SWT.FocusOut);
        IObservableValue personNameObserveValue = BeansObservables.observeValue(
                commandEntryPresenter, "command");

        bindingContext.bindValue(nameTextObserveWidget, personNameObserveValue, null, null);
        return bindingContext;
    }

    public CommandEntry getEntry() {
        return new CommandEntry(commandEntryPresenter.getCommand(),
                commandEntryPresenter.getWorkDir(), commandEntryPresenter.getApplyToProject(),
                commandEntryPresenter.getRefresOnComplete());
    }

    public void setWindowTitle(String windowTitle) {
        this.windowTitle = windowTitle;
    }

    public String getWindowTitle() {
        return windowTitle;
    }

    @Override
    protected void configureShell(Shell shell) {
        shell.setMinimumSize(new Point(450, 220));
        super.configureShell(shell);
        shell.setText(windowTitle);
    }

    @Override
    public boolean close() {
        bindingContext.dispose();
        return super.close();
    }
}
