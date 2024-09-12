package com.onkonfeton;

import java.util.Scanner;

public class UserInputHandler {
    private final Scanner scanner = new Scanner(System.in);

    public void inputInSimulation(Thread thread) {
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                thread.interrupt();
                break;
            }
        }
    }
}
