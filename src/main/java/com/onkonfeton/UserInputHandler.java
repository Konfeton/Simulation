package com.onkonfeton;

import java.util.Scanner;

public class UserInputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public static void inputInSimulation(Thread thread) {
        while (true) {
            System.out.println("Введите '0' чтобы остановить бесконечную симуляцию");
            String input = scanner.nextLine();
            if (input.equals("0")) {
                thread.interrupt();
                break;
            }
        }
    }
}
