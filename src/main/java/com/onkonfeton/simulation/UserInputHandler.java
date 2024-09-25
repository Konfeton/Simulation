package com.onkonfeton.simulation;

import java.io.IOException;
import java.util.Scanner;

public class UserInputHandler implements Runnable{
    public static final String COMMAND_STOP = "0";
    private final Scanner scanner = new Scanner(System.in);
    private final Thread thread;

    public UserInputHandler(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                if (System.in.available() > 0) {
                    String input = scanner.nextLine();
                    if (input.equals(COMMAND_STOP)) {
                        thread.interrupt();
                        break;
                    }
                }
            } catch (IOException ignored) {

            }
        }
    }
}
