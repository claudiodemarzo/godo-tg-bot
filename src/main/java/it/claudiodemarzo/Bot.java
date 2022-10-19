package it.claudiodemarzo;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.File;
import java.util.Scanner;

public class Bot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "godo_robot";
    }

    @Override
    public String getBotToken() {
        try (Scanner s = new Scanner(new File("token"))) {
            return s.nextLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update.toString());
        if(update.hasMessage()){
            Message message = update.getMessage();
            if(message.hasText()){
                String text = message.getText();
                if(text.toLowerCase().contains("godo") || text.toLowerCase().contains("goduto") || text.toLowerCase().contains("godere") || text.toLowerCase().contains("gode")){
                    SendMessage sm = new SendMessage();
                    sm.setChatId(message.getChatId());
                    sm.setReplyToMessageId(message.getMessageId());
                    sm.setText("Godo");
                    try {
                        execute(sm);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    SendAnimation sa = new SendAnimation();
                    sa.setChatId(message.getChatId());
                    sa.setReplyToMessageId(message.getMessageId());
                    sa.setAnimation(new InputFile(new File("IMG_5492.mp4")));
                    try {
                        execute(sa);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

}
