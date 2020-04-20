package com.bot.ribot.handler.state;

import com.bot.ribot.model.LineUser;

import org.springframework.stereotype.Component;

@Component
public class ChooseGameState extends State {
    public static final String DB_COL_NAME = "CHOOSE_GAME";

    public String register(String userId, String displayName){
        return "Anda sudah terdaftar di Ri-Bot. Silahkan masukkan perintah lain";
    }

    public String makeSession(String userId){
        return "Perintah yang anda masukkan salah. Silahkan pilih game yang ingin anda mainkan : tenis meja\n tinju";
    }

    public String Others(String userId, String command){
        LineUser user = lineUserRepository.findLineUserByUserId(userId);
        if(command yang dimasukkan adalah permainan yang diperbolehkan){
            user.setState(ChooseTimeState.DB_COL_NAME);
            lineUserRepository.save(user);
            return "Masukkan waktu bermain anda";
        }else{
            return "Perintah yang anda masukkan salah. Silahkan pilih game yang ingin anda mainkan : tenis meja\n tinju";
        }
    }
}