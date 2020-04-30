package com.bot.ribot.handler.state;

import com.bot.ribot.handler.message.Messages;
import com.bot.ribot.model.LineUser;
import org.springframework.stereotype.Component;

@Component
public class UnregisteredState extends State {
    public static final String DB_COL_NAME = "UNREGISTERED";

    /**
     * Handle when user's want to register to Ri-Bot.
     */
    public String register(String userId, String displayName) {
        //TO DO: Ilangin Exception
        try {
            LineUser newUser = new LineUser(userId, displayName);
            lineUserRepository.save(newUser);


            return Messages.SUCCESSFULLY_REGISTERED;
        } catch (Exception e) {
            return e.toString();
        }
    }

    public String makeSession(String userId) {
        return Messages.UNREGISTERED_WRONG_COMMAND;
    }

    public String others(String userId, String command) {
        return Messages.UNREGISTERED_WRONG_COMMAND;
    }
}