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

    /**
    * Handle when user's input /makesession command.
    */
    public String makeSession(String userId) {
        StringBuilder messages = new StringBuilder();
        messages.append("Perintah yang anda masukkan salah, "
                + "Silahkan pilih game yang ingin anda mainkan :");
        for (String game : Messages.availableGame) {
            messages.append("\n");
            messages.append(game);
        }
        return messages.toString();
    }

    /**
     * Handle when user's want to choose what game he/she want to play.
     */
    public String others(String userId, String command) {
        LineUser user = lineUserRepository.findLineUserByUserId(userId);
        //TO DO: true diganti command yang dimasukkan adalah permainan yang diperbolehkan
        //String idSalman = "U736daa71fa827df41b58e025e71dbc44"
        //TextMessage textMessage = new TextMessage(command+" "+Messages.availableGame.toString());
        //lineMessagingClient.pushMessage(new PushMessage(idSalman, textMessage));
        if (Messages.isAvailableGame(command)) {
            user.setState(ChooseTimeState.DB_COL_NAME);
            lineUserRepository.save(user);
            try {
                MatchSession match = new MatchSession(user, command);
                matchSessionRepository.save(match);
                return Messages.CHOOSE_GAME_SUCCESS;
            } catch (Exception e) {
                return e.toString();
            }
        } else {
            StringBuilder messages = new StringBuilder();
            messages.append("Perintah yang anda masukkan salah, Silahkan pilih game"
                        + "yang ingin anda mainkan :");
            for (String game : Messages.availableGame) {
                messages.append("\n");
                messages.append(game);
            }
            return messages.toString();
        }
    }
}