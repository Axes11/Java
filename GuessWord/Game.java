import java.util.Locale;
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
        String wordFromUser;
        System.out.println(wordToGuess);

        do{

            do{
                System.out.print("Type your word: ");
                wordFromUser = scanner.nextLine();

                if(wordFromUser.length() != 5) {
                    System.out.println("Your word must be equal 5 letters");
                }
            }while(wordFromUser.length() != 5);

            String[] wordFromUserArr = wordFromUser.split("");
            String[] wordToGuessArr = wordToGuess.split("");

            StringBuilder result = new StringBuilder();

            for (int i = 0; i < wordFromUserArr.length; i++) {
                if (wordFromUserArr[i].equals(wordToGuessArr[i])) {
                    result.append(wordFromUserArr[i].toUpperCase(Locale.ROOT));
                }
                else if (wordToGuess.contains(wordFromUserArr[i])) {
                    result.append("(").append(wordFromUserArr[i]).append(")");
                }
                else {
                    result.append(wordFromUserArr[i]);
                }
            }

            System.out.println(result);

            if(Objects.equals(wordFromUser, wordToGuess)){
                isGuessed = true;
                System.out.print("You guessed! Want try again? (yes/no): ");
                restart = scanner.nextLine();
                    if(Objects.equals(restart, "yes")){
                        restartGame();
                        System.out.println("\n Game restarted! \n");
                    }else{
                        break;
                    }
            }else{
                if(attempts >= 3 && !isGuessed){
                    System.out.print("Right word was: " + wordToGuess + ". You lose! You want try again? (yes/no): ");
                    restart = scanner.nextLine();
                    if(Objects.equals(restart, "yes")){
                        restartGame();
                    }else{
                        break;
                    }
                }
                attempts += 1;
            }
        }while(attempts < 4 && !isGuessed);

        return 1;
    }

    public void restartGame(){
        wordToGuess = getRandomWord();
        attempts = 0;
        isGuessed = false;
        System.out.println(wordToGuess);
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