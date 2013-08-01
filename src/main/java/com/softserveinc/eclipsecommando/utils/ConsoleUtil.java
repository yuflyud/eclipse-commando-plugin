package com.softserveinc.eclipsecommando.utils;

import java.io.IOException;
import java.io.OutputStream;

import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

public class ConsoleUtil {

    public static MessageConsole findConsole(String name) {
        ConsolePlugin plugin = ConsolePlugin.getDefault();
        IConsoleManager conMan = plugin.getConsoleManager();
        IConsole[] existing = conMan.getConsoles();
        for (int i = 0; i < existing.length; i++)
            if (name.equals(existing[i].getName()))
                return (MessageConsole) existing[i];
        // no console found, so create a new one
        MessageConsole myConsole = new MessageConsole(name, null);
        conMan.addConsoles(new IConsole[] { myConsole });
        return myConsole;
    }

    public static MessageConsoleStream startConsole(String consoleName) {
        MessageConsole myConsole = ConsoleUtil.findConsole(consoleName);
        MessageConsoleStream out = myConsole.newMessageStream();
        ConsolePlugin.getDefault().getConsoleManager().showConsoleView(myConsole);
        return out;
    }

    public static void writeProcessOutputToStream(Process process, OutputStream stream)
            throws IOException {
        try {
            StreamUtil.inToOutStream(process.getInputStream(), stream);
        } finally {
            stream.close();
        }
    }

    public static void writeProcessOutputToConsole(Process process, String consoleName)
            throws IOException {
        writeProcessOutputToStream(process, startConsole(consoleName));
    }
}
