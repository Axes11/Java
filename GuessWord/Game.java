import java.util.Objects;
import java.util.Scanner;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Game {
    public String wordToGuess = getRandomWord();
    public Scanner scanner = new Scanner(System.in);
    public boolean isGuessed = false;
    public int attempts = 0;

    public int game(String word){
        String restart;

        System.out.println(wordToGuess);

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

    public void restartGame(){
        wordToGuess = getRandomWord();
        attempts = 0;
        isGuessed = false;
    }

    public String getRandomWord(){
        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://random-word-api.herokuapp.com/word?length=5"))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String responseBody = response.body();

            return responseBody.replace("[", "").replace("]", "").replace("\"", "");
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }
}