package com.onkonfeton;

import com.onkonfeton.map.MapConfigurator;
import com.onkonfeton.map.WorldMap;
import com.onkonfeton.render.ConsoleRenderer;
import com.onkonfeton.render.Renderer;
import com.onkonfeton.simulation.Simulation;
import com.onkonfeton.simulation.SimulationController;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    private static final String COMMAND_CREATE_NEW_SIMULATION = "1";
    private static final String COMMAND_EXIT_FROM_MENU = "2";

    public static void main(String[] args) {
        while (true) {
            System.out.println("Добро пожаловать в симуляцию!");
            System.out.printf("%s) Создать новую симуляцию\n",COMMAND_CREATE_NEW_SIMULATION );
            System.out.printf("%s) Выйти\n", COMMAND_EXIT_FROM_MENU);
            System.out.print("Выбор: ");

            switch (scanner.nextLine()) {
                case COMMAND_CREATE_NEW_SIMULATION -> {
                    MapConfigurator mapConfigurator = new MapConfigurator();
                    WorldMap map = mapConfigurator.createMap();
                    Renderer renderer = new ConsoleRenderer();
                    Simulation simulation = new Simulation(map, renderer);
                    SimulationController simulationController = new SimulationController(simulation);
                    simulationController.start();
                }
                case COMMAND_EXIT_FROM_MENU -> {
                    return;
                }
                default -> System.out.println("Неверный ввод! Попробуйте ещё раз.");
            }
        }
    }



}

