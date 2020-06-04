package com.bot.ribot.handler.state;

import com.bot.ribot.handler.message.Messages;
import com.bot.ribot.model.LineUser;
import com.bot.ribot.repository.LineUserRepository;
import com.bot.ribot.repository.MatchSessionRepository;
import com.linecorp.bot.client.LineMessagingClient;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;



public abstract class State {
    @Autowired
    LineMessagingClient lineMessagingClient;
    @Autowired
    LineUserRepository lineUserRepository;
    @Autowired
    MatchSessionRepository matchSessionRepository;

    public abstract String register(String userId, String displayName);
    
    public abstract String makeSession(String userId);

    public abstract String others(String userId, String command) throws ParseException;

    /**
     * Handle when user's want to remind the rival.
     */
    public String remindRival(String userId) {
        LineUser user = lineUserRepository.findLineUserByUserId(userId);
        //LineUser others = lineUserRepository.findRivalByUserId(userId);

        //tambahin fungsi buat kirim pesan ke rival (pakenya push messages)
        return Messages.REMIND_SUCCESSFUL;
    }

    /**
     * Handle to toggle and Get Notification.
     */
    public String toggleGetNotification(String userId) {
        LineUser user = lineUserRepository.findLineUserByUserId(userId);
        String tambahan = " ";
        Boolean getNotification = user.getGetNotification();

        if (getNotification) {
            tambahan = " tidak";
        }
        
        user.setGetNotification(!getNotification);
        return Messages.TOGGLE_SUCCESSFUL + tambahan + " akan mendapatkan notifikasi";
    }
}