package com.onkonfeton;

import com.onkonfeton.entity.Entity;

public class ConsoleRenderer implements Renderer {

    private static final String EMPTY_CELL = "\uD83D\uDFEB";
    private static final String SPRITE_TREE = "\uD83C\uDF32";
    private static final String SPRITE_ROCK = "⛰\uFE0F";
    private static final String SPRITE_GRASS = "\uD83C\uDF40";
    private static final String SPRITE_HERBIVORE = "\uD83D\uDC07";
    private static final String SPRITE_PREDATOR = "\uD83E\uDD8A";
    private static final String SPRITE_CARROT = "\uD83E\uDD55";

    public void render(WorldMap map){
        for (int x = 0; x < map.getMaxWorldX(); x++) {
            for (int y = 0; y < map.getMaxWorldY(); y++) {
                Coordinates coordinates = new Coordinates(x, y);

                if (map.isSquareEmpty(coordinates)) {
                    System.out.print(EMPTY_CELL);
                }else{
                    Entity entity = map.getEntityByCoordinates(coordinates);
                    System.out.print((getSpriteForEntity(entity)));
                }

            }
            System.out.println();
        }
    }

    private String getSpriteForEntity(Entity entity) {
        return switch (entity.getClass().getSimpleName()) {
            case "Tree" -> SPRITE_TREE;
            case "Rock" -> SPRITE_ROCK;
            case "Grass" -> SPRITE_GRASS;
            case "Carrot" -> SPRITE_CARROT;
            case "Herbivore" -> SPRITE_HERBIVORE;
            case "Predator" -> SPRITE_PREDATOR;
            default -> throw new IllegalArgumentException("Unknown entity type: " + entity.getClass().getSimpleName());
        };
    }
}
