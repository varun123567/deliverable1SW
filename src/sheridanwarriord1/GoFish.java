/**
 * A simple implementation of the Go Fish card game.
 * Authors: Varun, Kundan Kumar, Bikramjit Singh, Irtiza Nazir
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GoFish {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of players: ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine();

        GoFishGame game = new GoFishGame();

        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Enter the name of Player " + i + ": ");
            String playerName = scanner.nextLine();
            Player player = new Player(playerName);
            game.addPlayer(player);
        }

        game.start();
    }
}