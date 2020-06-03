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

    // TO DO: ini bakal diganti object match session
    private MatchSession newMatchSession;

    public NotifyUser(){

    }

    public void setNewMatchSession(MatchSession matchSession) {
        this.newMatchSession = matchSession;
        notifyActiveUser();
    }

    /** 
     * Method for notify all active users
     * When there is new matchSession created.
    */
    public void notifyActiveUser() {
        List<LineUser> lineUsers = lineUserRepository.findAllLineUser();
        String greetings = "Telah dibuat match baru:\n";
        
        TextMessage text = new TextMessage("berhasil masuk");
        lineMessagingClient.pushMessage(new PushMessage("U736daa71fa827df41b58e025e71dbc44", text));
        for (LineUser lineUser : lineUsers) {
            text = new TextMessage("isi " + lineUser.getDisplayName());
            lineMessagingClient.pushMessage(
                    new PushMessage("U736daa71fa827df41b58e025e71dbc44", text));
            Boolean userGetNotification = lineUser.getGetNotification();
            if (userGetNotification) {
                TextMessage textMessage = new TextMessage(greetings + newMatchSession.toString());
                lineMessagingClient.pushMessage(new PushMessage(lineUser.getUserId(), textMessage));
            }
        }
    }
}