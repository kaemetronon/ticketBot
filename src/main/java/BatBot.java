import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import service.Handler;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BatBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            long chat_id = update.getMessage().getChatId();
            String response;

            Handler handler = new Handler(update.getMessage().getText());
            if (handler.getCorrect())
                response = handler.action();
            else response = handler.getMessage();

            SendMessage message = new SendMessage()
                    .setChatId(chat_id)
                    .setText(response);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }


    private String getAccessToken() {
        Properties prop = new Properties();
        String accessToken = null;

        try {
            prop.load(new FileInputStream("src/main/resources/botconfig.properties"));
            accessToken = prop.getProperty("token");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка при загрузке файда конфигурации");
        }
        return accessToken;
    }

    private String getUsername() {
        Properties prop = new Properties();
        String username = null;

        try {
            prop.load(new FileInputStream("src/main/resources/botconfig.properties"));
            username = prop.getProperty("username");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка при загрузке файда конфигурации");
        }
        return username;
    }

    @Override
    public String getBotUsername() {
        return getUsername();
    }

    @Override
    public String getBotToken() {
        return getAccessToken();
    }

}
