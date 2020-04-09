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

import java.util.Objects;
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
        String pesan = messageEvent.getMessage().getText();
        String[] pesanSplit = pesan.split(" ");
        String jawaban;
        if(Objects.equals(pesan.charAt(0), '/')) {
            pesan.toLowerCase();
        }

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
            case("/showSummary"):
                // Mengambil database dari detail session yang dibuat pada command /makeSession
                jawaban = "Summary sedang ditunjukkan!\nMaaf command ini masih dalam tahap pengembangan";
                break;
            case("/showMenu"):
                // Menunjukkan menu berisi command yang dapat dilakukan user
                jawaban = "Command Menu :\n1. /register\n2. /makeSession\n3. /findRival\n4. /remindRival\n5. /showSummary\nUntuk memunculkan menu ini lagi ketik : /showMenu";
                break;
            default:
                jawaban = "Maaf, command yang anda berikan salah:(\nUntuk mengetahui command yang dapat anda lakukan ketik :\n/showMenu";
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
