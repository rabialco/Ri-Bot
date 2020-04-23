package com.bot.ribot;

import com.bot.ribot.handler.state.State;
import com.bot.ribot.handler.state.helper.StateHelper;
import com.bot.ribot.repository.LineUserRepository;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.source.Source;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
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

    @Autowired
    private LineUserRepository lineUserRepository;

    @Autowired
    private StateHelper stateHelper;

    LogManager lgmngr = LogManager.getLogManager();
    Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);

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
    public void handleTextEvent(MessageEvent<TextMessageContent> messageEvent)
        throws ExecutionException, InterruptedException {

        Source source = messageEvent.getSource();
        String replyToken = messageEvent.getReplyToken();

        String command = getCommand(messageEvent);
        String userId = source.getUserId();
        String displayName = getUserDisplayName(userId);
        State state = stateHelper.getUserState(userId);
        
        String jawaban;
        String devName = "Lah nama salah satu Developer Ri-Bot nih";

        switch (command) {
            case("alco"):
                jawaban = devName;
                break;
            case("salman"):
                jawaban = devName;
                break;
            case("ryan"):
                jawaban = devName;
                break;
            case("yasmin"):
                jawaban = devName;
                break;
            case("raul"):
                jawaban = devName;
                break;
            case("/showsummary"):
                // Mengambil database dari detail session yang dibuat pada command /makeSession
                jawaban = "Summary sedang ditunjukkan!\n"
                        + "Maaf command ini masih dalam tahap pengembangan";
                break;
            case("/showmenu"):
                // Menunjukkan menu berisi command yang dapat dilakukan user
                jawaban = "Command Menu :\n1. /register\n2. /makeSession\n"
                        + "3. /remindRival\n4. /showSummary\n"
                        + "Untuk memunculkan menu ini lagi ketik : /showMenu";
                break;
            case("/makesession"):
                jawaban = state.makeSession(userId);
                break;
            case("/register"):
                jawaban = state.register(userId, displayName);
                break;
            case("/remindrival"):
                jawaban = state.remindRival(userId);
                break;
            default:
                jawaban = state.others(userId, command);
        }
        handleReplyEvent(replyToken, jawaban);
    }


    private void handleReplyEvent(String replyToken, String jawaban) {
        TextMessage jawabanDalamBentukTextMessage = new TextMessage(jawaban);
        try {
            lineMessagingClient
                    .replyMessage(new ReplyMessage(replyToken, jawabanDalamBentukTextMessage))
                    .get();
        } catch (InterruptedException | ExecutionException e) {
            log.log(Level.INFO, "Error while sending message");
            Thread.currentThread().interrupt();
        }
    }

    public String getCommand(MessageEvent<TextMessageContent> event) {
        return event.getMessage().getText().split(" ")[0].toLowerCase();
    }

    public String getUserDisplayName(String userId)
        throws ExecutionException, InterruptedException {
        return lineMessagingClient.getProfile(userId).get().getDisplayName();
    }
}