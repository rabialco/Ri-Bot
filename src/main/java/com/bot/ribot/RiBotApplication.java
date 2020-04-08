package com.bot.ribot;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.Random;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
@LineMessageHandler
public class RiBotApplication extends SpringBootServletInitializer {

    @Autowired
    private LineMessagingClient lineMessagingClient;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(RiBotApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(RiBotApplication.class, args);
    }

    @EventMapping
    public void handleTextEvent(MessageEvent<TextMessageContent> messageEvent){
        String pesan = messageEvent.getMessage().getText().toLowerCase();
        String[] pesanSplit = pesan.split(" ");
        String jawaban;
        switch (pesanSplit[0]){
            case("alco"):
                jawaban = "Lah nama salah satu Developer Ri-Bot nih";
                break;
            case("salman"):
                jawaban = "Lah nama salah satu Developer Ri-Bot nih";
                break;
            case("ryan"):
                jawaban = "Lah nama salah satu Developer Ri-Bot nih";
                break;
            case("yasmin"):
                jawaban = "Lah nama salah satu Developer Ri-Bot nih";
                break;
            case("raul"):
                jawaban = "Lah nama salah satu Developer Ri-Bot nih";
                break;
            default:
                jawaban = "Ri-Bot sedang dalam tahap pengembangan!";
        }
        String replyToken = messageEvent.getReplyToken();
        handleReplyEvent(replyToken, jawaban);
    }

    private void handleReplyEvent(String replyToken, String jawaban){
        TextMessage jawabanDalamBentukTextMessage = new TextMessage(jawaban);
        try {
            lineMessagingClient
                    .replyMessage(new ReplyMessage(replyToken, jawabanDalamBentukTextMessage))
                    .get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Ada error saat ingin membalas chat");
        }
    }

}
