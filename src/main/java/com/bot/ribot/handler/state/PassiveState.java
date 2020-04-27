package com.bot.ribot.handler.state;

import com.bot.ribot.model.LineUser;
import org.springframework.stereotype.Component;

@Component
public class PassiveState extends State {
    public static final String DB_COL_NAME = "PASSIVE";

    public String register(String userId, String displayName) {
        return "Anda sudah terdaftar di Ri-Bot. Silahkan masukkan perintah lain";
    }

    public String makeSession(String userId) {
        return "Perintah yang anda masukkan salah. "
                + "Silahkan selesaikan permainan menggunakan command /finish";
    }

    /**
     * Handle when user's want to finish the game.
     */
    public String others(String userId, String command) {
        LineUser user = lineUserRepository.findLineUserByUserId(userId);
        if (command.equals("/finish")) {
            user.setState(ActiveState.DB_COL_NAME);
            lineUserRepository.save(user);
            return "Permainan telah diselesaikan. "
                    + "Silahkan gunakan command /makeSession untuk membuat permainan baru";
        } else {
            return "Perintah yang anda masukkan salah. "
                    + "Silahkan selesaikan permainan menggunakan command /finish";
        }
    }
}