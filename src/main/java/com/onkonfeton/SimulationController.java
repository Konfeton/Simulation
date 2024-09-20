package com.onkonfeton;

import java.util.Scanner;

public class SimulationController {

    private static final String COMMAND_NEXT_TURN = "1";
    private static final String COMMAND_START_SIMULATION = "2";
    private static final String COMMAND_EXIT_FROM_SIMULATION = "3";

    private final Scanner scanner = new Scanner(System.in);

    private final Simulation simulation;

    private boolean exit = false;

    public SimulationController(Simulation simulation) {
        this.simulation = simulation;
    }

    public void start() {
        while (!simulation.isOver() && !exit) {
            printControls();
            String input = scanner.nextLine();
            handleInput(input);
        }
    }

    private void printControls() {
        System.out.println("Выберите действие");
        System.out.println("1) Сделать один ход");
        System.out.println("2) Запустить бесконечный цикл");
        System.out.println("3) Выйти");
        System.out.print("Выбор: ");
    }

    public void handleInput(String input) {
        switch (input) {
            case COMMAND_NEXT_TURN -> {
                simulation.makeTurn();
            }
            case COMMAND_START_SIMULATION -> {
                Thread simulationThread = new Thread(simulation::startInfinite);
                UserInputHandler userInputHandler = new UserInputHandler(simulationThread);
                Thread userInputHandlerThread = new Thread(userInputHandler);

                simulationThread.start();
                userInputHandlerThread.start();

                waitForSimulationToStop(simulationThread);
                stopInputHandling(userInputHandlerThread);
            }
            case COMMAND_EXIT_FROM_SIMULATION -> {
                exit = true;
            }
            default -> System.out.println("Неверный ввод! Попробуйте ещё раз");
        }
    }

    private void waitForSimulationToStop(Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void stopInputHandling(Thread thread1) {
        thread1.interrupt();
    }
}
