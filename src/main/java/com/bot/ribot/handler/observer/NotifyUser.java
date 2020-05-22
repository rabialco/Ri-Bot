package com.bot.ribot.handler.observer;

import com.bot.ribot.handler.state.ActiveState;
import com.bot.ribot.model.LineUser;
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

    //TO DO: ini bakal diganti object match session
    private String newMatchSession;

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
            String userState = lineUser.getState();
            if (userState.equals(ActiveState.DB_COL_NAME)) {
                TextMessage textMessage = new TextMessage(newMatchSession);
                lineMessagingClient.pushMessage(new PushMessage(lineUser.getUserId(), textMessage));
            }
        }
    }
}