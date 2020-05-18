package com.bot.ribot.handler.message;

public class Messages {
    //semua string cetak cetak simppan disini biar rapi

    public static final String SHOW_SUMMARY_MESSAGE = "Summary sedang ditunjukkan!\n"
            + "Maaf command ini masih dalam tahap pengembangan";

    public static final String SHOW_MENU_MESSAGE = "Command Menu :\n1. /register\n2. /makeSession\n"
            + "3. /remindRival\n4. /showSummary\n5. /showAvailableSession\n"
            + "Untuk memunculkan menu ini lagi ketik: \n/showMenu";

    public static final String ALREADY_REGISTERED = "Anda sudah terdaftar di Ri-Bot. "
            + "Silahkan masukkan perintah lain";

    public static final String ACTIVE_STATE_MAKE_SESSION = "Silahkan pilih game "
            + "yang ingin anda mainkan :\ntenis meja\ncatur\ntinju";

    public static final String ACTIVE_STATE_WRONG_COMMAND = "Perintah yang anda masukkan salah. "
            + "Silahkan buat game dengan perintah /makeSession";

    public static final String CHOOSE_GAME_WRONG_COMMAND = "Perintah yang anda masukkan salah. "
            + "Silahkan pilih game yang ingin anda mainkan : tenis meja\ncatir\ntinju";
    
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
            + "terkait game anda";
}