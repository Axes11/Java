import java.math.*;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        System.out.println("Rules: You have 4 attempts to guess right word \n 1. If your word have right letter in right place it will be capital like this 'A' \n 2. If it will be right but on another place it will be it brackets like this (a) \n");
        System.out.print("Start game? (yes/no): ");

        Scanner scanner = new Scanner(System.in);
        String startGame = scanner.nextLine();

        if (Objects.equals(startGame,"yes")) {
            Game game = new Game();
            game.game("penis");
        } else if (Objects.equals(startGame,"no")) {
            System.out.println("Game will not start.");
        } else {
            System.out.println("Invalid input. Please enter 'y' or 'n'.");
        }

        scanner.close();
    }
}
