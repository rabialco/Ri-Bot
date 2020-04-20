package com.bot.ribot.handler.state;

import com.bot.ribot.model.LineUser;

import org.springframework.stereotype.Component;

@Component
public class ChooseTimeState extends State {
    public static final String DB_COL_NAME = "CHOOSE_TIME";

    public String register(String userId, String displayName){
        return "Anda sudah terdaftar di Ri-Bot. Silahkan masukkan perintah lain";
    }

    public String makeSession(String userId){
        return "Perintah yang anda masukkan salah. Silahkan pilih waktu bermain";
    }

    public String Others(String userId, String command){
        LineUser user = lineUserRepository.findLineUserByUserId(userId);
        if(command yang dimasukkan adalah waktu yang valid){
            user.setState(PassiveState.DB_COL_NAME);
            lineUserRepository.save(user);
            return "Permainan berhasil dibuat. gunakan command /finish untuk menyelesaikan permainan";
        }else{
            return "Perintah yang anda masukkan salah. Silahkan pilih waktu bermain";
        }
    }
}