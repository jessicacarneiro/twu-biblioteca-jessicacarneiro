package com.twu.biblioteca.IO;

import java.io.PrintStream;

public class StreamPrinter {
    private PrintStream printStream;

    public StreamPrinter(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void printString(String string) {
        printStream.println(string);
    }
}
