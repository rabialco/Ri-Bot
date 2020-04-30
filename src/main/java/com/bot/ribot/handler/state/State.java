package com.bot.ribot.handler.state;

import com.bot.ribot.model.LineUser;
import com.bot.ribot.repository.LineUserRepository;
import com.linecorp.bot.client.LineMessagingClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;

public abstract class State {
    @Autowired
    LineMessagingClient lineMessagingClient;
    @Autowired
    LineUserRepository lineUserRepository;

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
        return "Rival anda sudah diingatkan terkait game anda";
    }
}