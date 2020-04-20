package com.twu.biblioteca.IO;

import java.io.PrintStream;

public class StreamPrinter {
    private PrintStream printStream;

    public StreamPrinter(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void printNewLineString(String string) {
        printStream.println(string);
    }

    public void printString(String string) { printStream.print(string); }
}
