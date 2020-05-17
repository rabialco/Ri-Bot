package com.bot.ribot.handler.state;

import com.bot.ribot.handler.message.Messages;
import com.bot.ribot.model.LineUser;
import com.bot.ribot.model.MatchSession;
import org.springframework.stereotype.Component;

@Component
public class ChooseGameState extends State {
    public static final String DB_COL_NAME = "CHOOSE_GAME";

    public String register(String userId, String displayName) {
        return Messages.ALREADY_REGISTERED;
    }

    public String makeSession(String userId) {
        return Messages.CHOOSE_GAME_WRONG_COMMAND;
    }

    /**
     * Handle when user's want to choose what game he/she want to play.
     */
    public String others(String userId, String command) {
        LineUser user = lineUserRepository.findLineUserByUserId(userId);
        //TO DO: true diganti command yang dimasukkan adalah permainan yang diperbolehkan
        if (Messages.availableGame.contains(command)) {
            user.setState(ChooseTimeState.DB_COL_NAME);
            lineUserRepository.save(user);
            MatchSession match = new MatchSession(user, command);
            return Messages.CHOOSE_GAME_SUCCESS;
        } else {
            return Messages.CHOOSE_GAME_WRONG_COMMAND;
        }
    }
}