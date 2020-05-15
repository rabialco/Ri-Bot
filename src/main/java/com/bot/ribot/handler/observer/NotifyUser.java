package com.bot.ribot.handler.observer;

import com.bot.ribot.model.LineUser;
import com.bot.ribot.repository.LineUserRepository;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class NotifyUser {
    @Autowired
    LineUserRepository lineUserRepository;

    @Autowired
    private LineMessagingClient lineMessagingClient;

    //TO DO: ini bakal diganti object match session
    private String newMatchSession;

    LogManager lgmngr = LogManager.getLogManager();
    Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public void setNewMatchSession(String newMatchSession) {
        this.newMatchSession = newMatchSession;
    }

    /** 
     * Method for notify all active users
     * When there is new matchSession created.
    */
    public void notifyActiveUser() {
        List<LineUser> lineUsers = lineUserRepository.findAllLineUser();
        for (LineUser lineUser : lineUsers) {
            handlePushMessageEvent(lineUser.getUserId(), newMatchSession);
        }
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
}