package com.onkonfeton;

import java.util.Scanner;

public class SimulationController {

    private static Scanner scanner = new Scanner(System.in);


    private static final String COMMAND_NEXT_TURN = "1";
    private static final String COMMAND_START_SIMULATION = "2";
    private static final String COMMAND_EXIT_FROM_SIMULATION = "3";
    private final Simulation simulation;

    public SimulationController(Simulation simulation) {
        this.simulation = simulation;
    }

    public void start() {

    }


    public void handle() {

        while (!simulation.isOver()) {
            System.out.println("Выберите действие");
            System.out.println("1) Сделать один ход");
            System.out.println("2) Запустить бесконечный цикл");
            System.out.println("3) Выйти");
            System.out.print("Выбор: ");

            switch (scanner.nextLine()) {
                case COMMAND_NEXT_TURN -> {
                    simulation.makeTurn();
                }
                case COMMAND_START_SIMULATION -> {


                }
                case COMMAND_EXIT_FROM_SIMULATION -> {
                    return;
                }
                default -> System.out.println("Неверный ввод! Попробуйте ещё раз");
            }
        }
    }
}
