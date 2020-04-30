package com.bot.ribot.handler.state;

import com.bot.ribot.handler.message.Messages;
import com.bot.ribot.model.LineUser;
import org.springframework.stereotype.Component;

@Component
public class ChooseTimeState extends State {
    public static final String DB_COL_NAME = "CHOOSE_TIME";

    public String register(String userId, String displayName) {
        return Messages.ALREADY_REGISTERED;
    }

    public String makeSession(String userId) {
        return Messages.CHOOSE_TIME_WRONG_COMMAND;
    }

    /**
     * Handle when user's want to choose what time he/she want to play.
     */
    public String others(String userId, String command) {
        LineUser user = lineUserRepository.findLineUserByUserId(userId);
        //TO DO: Ganti if condition dengan -> command yang dimasukkan adalah waktu yang valid 
        if (true) {
            user.setState(PassiveState.DB_COL_NAME);
            lineUserRepository.save(user);
            return Messages.CHOOSE_TIME_SUCCESS;
        } else {
            return Messages.CHOOSE_TIME_WRONG_COMMAND;
        }
    }
}