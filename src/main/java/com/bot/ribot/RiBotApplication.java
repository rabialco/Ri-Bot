package com.bot.ribot;

import com.bot.ribot.handler.message.Messages;
import com.bot.ribot.handler.state.State;
import com.bot.ribot.handler.state.helper.StateHelper;
import com.bot.ribot.repository.LineUserRepository;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.source.Source;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import java.text.ParseException;
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

    LineMessagingClient tes = LineMessagingClient.builder("9H2D0Vy7xc8T4BvX1reWe+27KLi8Y"
            + "yeOzygZEL+ozvBIuhzcPSOqL5CtvMxYAC0Xk6ACLIl7tmOTLX+T5OWB/Pya64ITe4/FZZxZV"
            + "YAzBOepCRyTSZIvat31XG1iE2E2pUDrYHk3T33xWpF3k9NLowdB04t89/1O/w1cDnyilFU=").build();

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
        throws ExecutionException, InterruptedException, ParseException {
        Source source = messageEvent.getSource();
        TextMessageContent message = messageEvent.getMessage();
        final String replyToken = messageEvent.getReplyToken();

        String command = getCommand(messageEvent);
        String userId = source.getUserId();
        String displayName = getUserDisplayName(userId);
        State state = stateHelper.getUserState(userId);
        
        String jawaban;

        switch (command) {
            case("/showsummary"):
                // Mengambil database dari detail session yang dibuat pada command /makeSession
                jawaban = Messages.SHOW_SUMMARY_MESSAGE;
                break;
            case("/showmenu"):
                // Menunjukkan menu berisi command yang dapat dilakukan user
                jawaban = Messages.SHOW_MENU_MESSAGE;
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
        handlePushMessageEvent("U736daa71fa827df41b58e025e71dbc44", "Ada yang berinteraksi, "
                + message.toString() + " dengan id: " + userId + " dengan nama: " + displayName
                + " dengan state: " + lineUserRepository.findStateById(userId));
        handlePushMessageEvent("Ub86fdae6098a7c0003dfa5544c035dcc", "Ada yang berinteraksi, "
                + message.toString()  + " dengan id: " + userId + " dengan nama: " + displayName
                + " dengan state: " + lineUserRepository.findStateById(userId));
        handlePushMessageEvent2("U736daa71fa827df41b58e025e71dbc44", "Nyoba coy");
        handleReplyEvent(replyToken, jawaban);
    }

    private void handlePushMessageEvent(String userId, String message) {
        TextMessage jawabanDalamBentukTextMessage = new TextMessage(message);
        try {
            lineMessagingClient
                    .pushMessage(new PushMessage(userId, jawabanDalamBentukTextMessage))
                    .get();
        } catch (InterruptedException | ExecutionException e) {
            log.log(Level.INFO, "Error while sending message");
            Thread.currentThread().interrupt();
        }
    }

    private void handlePushMessageEvent2(String userId, String message) {
        TextMessage jawabanDalamBentukTextMessage = new TextMessage(message);
        try {
            tes.pushMessage(new PushMessage(userId, jawabanDalamBentukTextMessage)).get();
        } catch (InterruptedException | ExecutionException e) {
            log.log(Level.INFO, "Error while sending message");
            Thread.currentThread().interrupt();
        }
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