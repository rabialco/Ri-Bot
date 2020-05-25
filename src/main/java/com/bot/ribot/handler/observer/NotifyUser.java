package com.bot.ribot.handler.observer;

import com.bot.ribot.handler.state.ActiveState;
import com.bot.ribot.model.LineUser;
import com.bot.ribot.model.MatchSession;
import com.bot.ribot.repository.LineUserRepository;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotifyUser {
    @Autowired
    LineUserRepository lineUserRepository;

    @Autowired
    private LineMessagingClient lineMessagingClient;

    private static NotifyUser notifyUser = null;

    // TO DO: ini bakal diganti object match session
    private MatchSession newMatchSession;

    private NotifyUser(){

    }

    /**
     * Singleton implementation.
     */
    public static NotifyUser getInstance() {
        if (notifyUser == null) {
            notifyUser = new NotifyUser();
        }
        return notifyUser;
    }

    public void setNewMatchSession(MatchSession newMatchSession) {
        this.newMatchSession = newMatchSession;
        this.notifyActiveUser();
    }

    /** 
     * Method for notify all active users
     * When there is new matchSession created.
    */
    public void notifyActiveUser() {
        List<LineUser> lineUsers = lineUserRepository.findAllLineUser();
        for (LineUser lineUser : lineUsers) {
            String userState = lineUser.getState();
            if (userState.equals(ActiveState.DB_COL_NAME)) {
                TextMessage textMessage = new TextMessage(newMatchSession.toString());
                lineMessagingClient.pushMessage(new PushMessage(lineUser.getUserId(), textMessage));
            }
        }
    }
}