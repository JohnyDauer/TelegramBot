package test;

import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class SimpleBot extends TelegramLongPollingBot {

    public static void main(String[] args) {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new SimpleBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getBotUsername() {
        return "JohnDauer_bot";
    }

    @Override
    public String getBotToken() {
        return "262343514:AAHHjTUfppdmJ__jbr60elbYApgZTfnCLVA";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            if (message.getText().equals("/help"))
                sendMsg(message, "Привет! Чё как?");
            else if (message.getText().equals(("жопа")))
                sendMsg(message, "Сам ты жопа!");
            else if (message.getText().equals(("Валера")))
                sendMsg(message, "Привет, Валера!");
            else
                sendMsg(message, "Не вкурил чё те надо");
        }
    }

    private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplayToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}