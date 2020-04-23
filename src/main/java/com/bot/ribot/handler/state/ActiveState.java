package com.bot.ribot.handler.state;

import com.bot.ribot.model.LineUser;
import org.springframework.stereotype.Component;

@Component
public class ActiveState extends State {
    public static final String DB_COL_NAME = "ACTIVE";

    public String register(String userId, String displayName) {
        return "Anda sudah terdaftar di Ri-Bot. Silahkan masukkan perintah lain";
    }

    /**
     * Handle when user's want to create a game.
     */
    public String makeSession(String userId) {
        LineUser user = lineUserRepository.findLineUserByUserId(userId);
        user.setState(ChooseGameState.DB_COL_NAME);
        lineUserRepository.save(user);
        
        //tambahin tapi nanti
        return "Silahkan pilih game yang ingin anda mainkan : tenis meja\n tinju";
    }

    public String others(String userId, String command) {
        return "Perintah yang anda masukkan salah. Silahkan pilih game "
                + "yang ingin anda mainkan : tenis meja\n tinju";
    }



}