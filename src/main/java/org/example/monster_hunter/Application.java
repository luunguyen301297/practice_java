package org.example.monster_hunter;

import org.example.monster_hunter.config.GameConfig;
import org.example.monster_hunter.game.Game;
import org.example.monster_hunter.validator.GameUtils;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        printGuideLine();
        var console = new Scanner(System.in);

        final var gameConfig = GameConfig.defaultConfig();
        GameUtils.validateGameConfig(gameConfig);

        final var game = new Game(gameConfig);

        String input = console.nextLine();
        while (! "e".equals(input)) {
            switch (input) {
                case "w":
                    game.hero().moveUp();
                    break;
                case "s":
                    game.hero().moveDown();
                    break;
                case "a":
                    game.hero().moveLeft();
                    break;
                case "d":
                    game.hero().moveRight();
                    break;
                case "i":
                    game.hero().shootUp();
                    break;
                case "k":
                    game.hero().shootDown();
                    break;
                case "j":
                    game.hero().shootLeft();
                    break;
                case "l":
                    game.hero().shootRight();
                    break;
                default:
                    break;
            }
            console = new Scanner(System.in);
            input = console.nextLine();
        }
        System.out.println("Exit!");
    }

    private static void printGuideLine() {
        System.err.println("H is hero");
        System.err.println("X is monster");
        System.err.println("When H is near X, print: Near the monster");
        System.err.println("When H reach X: Game over");
        System.err.println("-------------------------------------------------");
        System.err.println("Move up/down/left/right by: w/s/a/d");
        System.err.println("Shoot up/down/left/right by: i/k/j/l");
        System.err.println("Exit by: e");
        System.err.println("-------------------------------------------------");
    }
}
