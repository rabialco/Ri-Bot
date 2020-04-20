package com.bot.ribot;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

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

    /**
     * Handle the user's input from the input arguments.
     *
     * @case                   the handleTextEvent case input.
     * @default                if user's input contains case.
     */
    @EventMapping
    public void handleTextEvent(MessageEvent<TextMessageContent> messageEvent) {
        String pesan = messageEvent.getMessage().getText();
        String[] pesanSplit = pesan.split(" ");
        String jawaban;

        switch (pesanSplit[0]) {
            case("alco"):
            case("Alco"):
                jawaban = "Lah nama salah satu Developer Ri-Bot nih";
                break;
            case("salman"):
            case("Salman"):
                jawaban = "Lah nama salah satu Developer Ri-Bot nih";
                break;
            case("ryan"):
            case("Ryan"):
                jawaban = "Lah nama salah satu Developer Ri-Bot nih";
                break;
            case("yasmin"):
            case("Yasmin"):
                jawaban = "Lah nama salah satu Developer Ri-Bot nih";
                break;
            case("raul"):
            case("Raul"):
                jawaban = "Lah nama salah satu Developer Ri-Bot nih";
                break;
            case("/showSummary"):
                // Mengambil database dari detail session yang dibuat pada command /makeSession
                jawaban = "Summary sedang ditunjukkan!\n"
                        + "Maaf command ini masih dalam tahap pengembangan";
                break;
            case("/showMenu"):
                // Menunjukkan menu berisi command yang dapat dilakukan user
                jawaban = "Command Menu :\n1. /register\n2. /makeSession\n"
                        + "3. /remindRival\n4. /showSummary\n"
                        + "Untuk memunculkan menu ini lagi ketik : /showMenu";
                break;
            case("/makeSession"):
                jawaban = "Anda telah memilih untuk membuat match.\n"
                        + "Fitur ini sedang dalam pembuatan";
                break;
            case("/register"):
                jawaban = "Anda telah memilih untuk melakukan pendaftaran di Ri-Bot.\n"
                        + "Fitur ini masih dalam tahap pengembanagan";
                break;
            case("/remindRival"):
                jawaban = "Anda telah memilih untuk mengingatkan lawan.\n"
                        + "Fitur ini sedang dalam pembuatan";
                break;
            default:
                jawaban = "Maaf, command yang anda berikan salah:(\n"
                        + "Untuk mengetahui command yang dapat anda lakukan ketik :\n/showMenu";
        }
        String replyToken = messageEvent.getReplyToken();
        handleReplyEvent(replyToken, jawaban);
    }

    private void handleReplyEvent(String replyToken, String jawaban) {
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
