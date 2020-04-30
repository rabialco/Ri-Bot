package com.bot.ribot.handler.state;

import com.bot.ribot.handler.message.Messages;
import com.bot.ribot.model.LineUser;
import org.springframework.stereotype.Component;

@Component
public class PassiveState extends State {
    public static final String DB_COL_NAME = "PASSIVE";

    public String register(String userId, String displayName) {
        return Messages.ALREADY_REGISTERED;
    }

    public String makeSession(String userId) {
        return Messages.PASSIVE_STATE_WRONG_COMMAND;
    }

    /**
     * Handle when user's want to finish the game.
     */
    public String others(String userId, String command) {
        LineUser user = lineUserRepository.findLineUserByUserId(userId);
        if (command.equals("/finish")) {
            user.setState(ActiveState.DB_COL_NAME);
            lineUserRepository.save(user);
            return Messages.PASSIVE_STATE_SUCCESS;
        } else {
            return Messages.PASSIVE_STATE_WRONG_COMMAND;
        }
    }
}