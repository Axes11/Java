import java.util.Objects;
import java.util.Scanner;

public class Game {
    public String[] arrayWithWords = {"bread", "carrot", "potato", "tomato"};
    public String wordToGuess = generateRandomWord();
    public Scanner scanner = new Scanner(System.in);
    public boolean isGuessed = false;
    public int attempts = 0;

    public int game(String word){
        String restart;

        do{
            System.out.println("Type your word: ");
            String wordFromUser = scanner.nextLine();

            if(Objects.equals(wordFromUser, wordToGuess)){
                isGuessed = true;
                System.out.println("You guessed! Want try again? (yes/no): ");
                restart = scanner.nextLine();
                    if(Objects.equals(restart, "yes")){
                        restartGame();
                        System.out.println("\n Game restarted! \n");
                    }else{
                        break;
                    }
            }else{
                if(attempts >= 4 && isGuessed == false){
                    System.out.println("Right word was: " + wordToGuess + " You lose! You want try again? (y/n): ");
                    restart = scanner.nextLine();
                    if(Objects.equals(restart, "yes")){
                        restartGame();
                    }else{
                        break;
                    }
                }
                attempts += 1;
            }
        }while(attempts < 4 && isGuessed == false);

        return 1;
    }

    public String generateRandomWord(){
        int randomInt = (int)(Math.random() * 3);
        return arrayWithWords[randomInt];
    }

    public void restartGame(){
        wordToGuess = generateRandomWord();
        attempts = 0;
        isGuessed = false;
    }
}
