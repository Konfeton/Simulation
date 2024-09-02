package com.onkonfeton;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    private static final String COMMAND_CREATE_NEW_SIMULATION = "1";
    private static final String COMMAND_EXIT_FROM_MENU = "2";

    private static final String COMMAND_NEXT_TURN = "1";
    private static final String COMMAND_START_SIMULATION = "2";
    private static final String COMMAND_EXIT_FROM_SIMULATION = "3";



    public static void main(String[] args) {
        startMenu();
    }

    private static void startMenu() {

        while (true) {
            System.out.println("Добро пожаловать в симуляцию!");
            System.out.printf("%s) Создать новую симуляцию\n",COMMAND_CREATE_NEW_SIMULATION );
            System.out.printf("%s) Выйти\n", COMMAND_EXIT_FROM_MENU);
            System.out.print("Выбор: ");

            WorldMap map;

            switch (scanner.nextLine()) {
                case COMMAND_CREATE_NEW_SIMULATION -> {
                    MapConfigurator mapConfigurator = new MapConfigurator();
                    map = mapConfigurator.createMap();
                    simulationMenu(map);
                }
                case COMMAND_EXIT_FROM_MENU -> {
                    return;
                }
                default -> System.out.println("Неверный ввод! Попробуйте ещё раз.");
            }
        }
    }

    private static void simulationMenu(WorldMap map) {

        Simulation simulation = createSimulation(map);

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
                    Thread thread = new Thread(simulation::startInfinite);
                    thread.start();
                    UserInputHandler handler = new UserInputHandler();
                    handler.inputInSimulation(thread);
                }
                case COMMAND_EXIT_FROM_SIMULATION -> {
                    return;
                }
                default -> System.out.println("Неверный ввод! Попробуйте ещё раз");
            }
        }
    }

    private static Simulation createSimulation(WorldMap map) {
        Simulation simulation = new Simulation(
                map,
                new ConsoleRenderer()
        );

        return simulation;
    }


}

