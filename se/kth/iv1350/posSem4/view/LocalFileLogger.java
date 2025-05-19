package se.kth.iv1350.posSem4.view;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

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
    /**
     * Writes an error message and exception stack trace to the log file.
     *
     * @param message The error message.
     * @param ex      The exception to log.
     */
    public void logException(String message, Exception ex) {
        if (logStream == null) return;
        logStream.println("ERROR at " + LocalDateTime.now());
        logStream.println(message);
        ex.printStackTrace(logStream);
        logStream.println(); // Blank line between entries
    }
}