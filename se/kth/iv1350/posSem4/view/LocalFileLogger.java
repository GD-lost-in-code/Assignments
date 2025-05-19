package se.kth.iv1350.posSem4.view;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Simple file logger that writes to "log.txt" in current directory.
 */
public class LocalFileLogger {
    private PrintWriter logStream;

    public LocalFileLogger() {
        try {
            logStream = new PrintWriter(new FileWriter("log.txt", true), true);
        } catch (IOException ioe) {
            System.err.println("Could not initialize LocalFileLogger");
            ioe.printStackTrace();
        }
    }

    public void log(String message) {
        if (logStream != null) {
            logStream.println(message);
        }
    }
}