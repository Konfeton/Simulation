package com.onkonfeton;

import java.util.Scanner;

public class MapConfigurer {

    private static final int MIN_MAP_SIZE = 10;
    private static final int MAX_MAP_SIZE = 20;

    private final Scanner scanner = new Scanner(System.in);

    public WorldMap configure(){
        System.out.printf("Введите размер карты (в пределах от %d до %d)\n", MIN_MAP_SIZE, MAX_MAP_SIZE);

        System.out.println("Введите длину");
        int maxX = getUserInput();

        System.out.println("Введите высоту");
        int maxY = getUserInput();

        return new WorldMap(maxX, maxY);
    }

    private  int getUserInput() {

        while (true) {
            try {
                String input = scanner.nextLine();
                int maxSize = Integer.parseInt(input);
                if (maxSize >= MIN_MAP_SIZE && maxSize <= MAX_MAP_SIZE) {
                    return maxSize;
                }
                System.out.printf("Число не в диапазоне от %d до %d\n", MIN_MAP_SIZE, MAX_MAP_SIZE);
            } catch (NumberFormatException e) {
                System.out.println("Введите число");
            }
        }
    }
}
