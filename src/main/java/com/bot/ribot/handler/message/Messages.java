package com.bot.ribot.handler.message;

import java.util.ArrayList;
import java.util.Arrays;


public class Messages {
    /**
     * check whether the string is a valid game or not.
     * return true if a valid game, false if others.
     */
    public static boolean isAvailableGame(String check) {
        for (String game : availableGame) {
            if (game.equalsIgnoreCase(check)) {
                return true;
            }
        }
        return false;
    }
    //semua string cetak cetak simpan disini biar rapi

    public static final ArrayList<String> availableGame = new ArrayList<String>(
            Arrays.asList("Tenis Meja", "Catur", "Tinju", "Dota", "Mobile Legends")
    );

    public static final String SHOW_SUMMARY_MESSAGE = "Summary sedang ditunjukkan!\n"
            + "Maaf command ini masih dalam tahap pengembangan";

    public static final String SHOW_MENU_MESSAGE = "Command Menu :\n1. /register\n2. /makeSession\n"
            + "3. /remindRival\n4. /showSummary\n5. /showAvailableSession\n6./toggleNotification"
            + "Untuk memunculkan menu ini lagi ketik: \n/showMenu";

    public static final String ALREADY_REGISTERED = "Anda sudah terdaftar di Ri-Bot. "
            + "Silahkan masukkan perintah lain";

    public static final String ACTIVE_STATE_WRONG_COMMAND = "Perintah yang anda masukkan salah. "
            + "Silahkan buat game dengan perintah /makeSession";
    
    public static final String CHOOSE_GAME_SUCCESS =
            "Masukkan waktu bermain anda dengan format dd-MM-yyyy hh:mm";

    public static final String CHOOSE_TIME_WRONG_COMMAND = "Perintah yang anda masukkan salah."
            + " Silahkan pilih waktu bermain";

    public static final String CHOOSE_TIME_SUCCESS = "Permainan berhasil dibuat. "
            + "gunakan command /finish untuk menyelesaikan permainan";

    public static final String PASSIVE_STATE_WRONG_COMMAND = "Perintah yang anda masukkan salah. "
            + "Silahkan selesaikan permainan menggunakan command /finish";

    public static final String PASSIVE_STATE_SUCCESS = "Permainan telah diselesaikan. "
            + "Silahkan gunakan command /makeSession untuk membuat permainan baru";
    
    public static final String SUCCESSFULLY_REGISTERED = "Registrasi sukses. "
            + "Silahkan masukkan command /makeSession untuk membuat permainan baru";

    public static final String UNREGISTERED_WRONG_COMMAND = "Anda belum terdaftar pada"
            + " sistem Ri-Bot. Silahkan masukkan perintah /register untuk mendaftar";
    
    public static final String REMIND_SUCCESSFUL = "Rival anda sudah diingatkan "
            + "terkait game anda. "
            + "Rival akan diingatkan kembali setiap 10 menit";

    public static final String TOGGLE_SUCCESSFUL = "Toggle notifikasi berhasil"
            + "anda saat ini";
}