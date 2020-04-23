package com.bot.ribot.handler.state;

import com.bot.ribot.model.LineUser;
import org.springframework.stereotype.Component;

@Component
public class UnregisteredState extends State {
    public static final String DB_COL_NAME = "UNREGISTERED";

    /**
     * Handle when user's want to register to Ri-Bot.
     */
    public String register(String userId, String displayName) {
        LineUser newUser = new LineUser(userId, displayName);
        lineUserRepository.save(newUser);

        return "Registrasi sukses. "
                + "Silahkan masukkan command /makeSession untuk membuat permainan baru";
    }

    public String makeSession(String userId) {
        return "Anda belum terdaftar pada sistem Ri-Bot. "
                + "Silahkan masukkan perintah /register untuk mendaftar";
    }

    public String others(String userId, String command) {
        return "Anda belum terdaftar pada sistem Ri-Bot. "
                + "Silahkan masukkan perintah /register untuk mendaftar";
    }
}