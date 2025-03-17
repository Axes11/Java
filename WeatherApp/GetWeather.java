import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetWeather {
    public void getWeather() {
        try {
            // Указываем URL с реальным API-ключом
            String apiKey = ""; // Ваш API-ключ
            String urlString = "https://api.openweathermap.org/data/.0/onecall?lat=33.44&lon=-94.04&exclude=hourly,daily&appid=" + apiKey;

            // Создаем объект URL
            URL url = new URL(urlString);

            // Устанавливаем соединение
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Проверяем код ответа
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // Код 200 - Успешный запрос
                // Читаем ответ
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Выводим результат
                System.out.println("Response from API:");
                System.out.println(response.toString());
            } else {
                System.out.println("Error: Server returned response code " + responseCode);
            }

            // Закрываем соединение
            connection.disconnect();

        } catch (Exception e) {
            // Выводим стек вызовов при ошибке
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        GetWeather weather = new GetWeather();
        weather.getWeather();
    }
}
