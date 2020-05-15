package com.bot.ribot.handler.state;

import com.bot.ribot.handler.message.Messages;
import com.bot.ribot.model.LineUser;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static Pattern dateTimePattern = Pattern.compile(
            "^(([1-9]|([012][0-9])|(3[01]))-([0]{0,1}[1-9]|1[012])-(19|20)[0-9]{2} "
                    + "([0-1]?[0-9]|2?[0-3]):([0-5][0-9]))$");

    private static SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("dd-MM-yyyy hh:mm");

    private boolean isDateTimeValid(String dateTime) throws ParseException {
        Matcher matcher = dateTimePattern.matcher(dateTime);
        if (matcher.matches()) {
            Date date1 = new Date();
            Date date2 = dateTimeFormatter.parse(dateTime);
            handlePushMessageEvent("U736daa71fa827df41b58e025e71dbc44", "Ada yang memilih time, "
                  + date1.toString() + "\n" + date2.toString() + "\n");
            return (date1.compareTo(date2) <= 0);
        } else {
            return false;
        }
    }

    //This block of code dedicated to salman's debug
    @Autowired
    private LineMessagingClient client = LineMessagingClient.builder("9H2D0Vy7xc8T4BvX1reWe+27KLi8Y"
            + "yeOzygZEL+ozvBIuhzcPSOqL5CtvMxYAC0Xk6ACLIl7tmOTLX+T5OWB/Pya64ITe4/FZZxZV"
            + "YAzBOepCRyTSZIvat31XG1iE2E2pUDrYHk3T33xWpF3k9NLowdB04t89/1O/w1cDnyilFU=").build();



    LogManager lgmngr = LogManager.getLogManager();
    Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private void handlePushMessageEvent(String userId, String message) {
        TextMessage jawabanDalamBentukTextMessage = new TextMessage(message);
        try {
            client.pushMessage(new PushMessage(userId, jawabanDalamBentukTextMessage)).get();
        } catch (InterruptedException | ExecutionException e) {
            log.log(Level.INFO, "Error while sending message");
            Thread.currentThread().interrupt();
        }
    }
    //END OF BLOCK
    
    /**
     * Handle when user's want to choose what time he/she want to play.
     */
    public String others(String userId, String command) throws ParseException {
        LineUser user = lineUserRepository.findLineUserByUserId(userId);
        //TO DO: Ganti if condition dengan -> command yang dimasukkan adalah waktu yang valid

        if (isDateTimeValid(command)) {
            user.setState(PassiveState.DB_COL_NAME);
            lineUserRepository.save(user);
            return Messages.CHOOSE_TIME_SUCCESS;
        } else {
            return Messages.CHOOSE_TIME_WRONG_COMMAND;
        }
    }
}