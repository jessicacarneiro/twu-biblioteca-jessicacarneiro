package com.twu.biblioteca.IO;

import java.io.BufferedReader;
import java.io.IOException;

public class InputReceiver {
    private BufferedReader bufferedReader;

    public InputReceiver(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public String receiveUserInput() throws IOException {
        return this.bufferedReader.readLine();
    }
}