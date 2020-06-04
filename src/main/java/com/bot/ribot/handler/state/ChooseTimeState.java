package com.bot.ribot.handler.state;

import com.bot.ribot.handler.message.Messages;
import com.bot.ribot.handler.observer.NotifyUser;
import com.bot.ribot.model.LineUser;
import com.bot.ribot.model.MatchSession;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
                    + "([0-1][0-9]|2[0-3]):([0-5][0-9]))$");

    private static SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("dd-MM-yyyy hh:mm");

    private boolean isDateTimeValid(String dateTime) throws ParseException {
        Matcher matcher = dateTimePattern.matcher(dateTime);
        if (matcher.matches()) {
            Date date1 = new Date();
            Date date2 = dateTimeFormatter.parse(dateTime);
            return (date1.compareTo(date2) <= 0);
        } else {
            return false;
        }
    }

    /**
     * Handle when user's want to choose what time he/she want to play.
     */
    public String others(String userId, String command) throws ParseException {
        LineUser user = lineUserRepository.findLineUserByUserId(userId);
        MatchSession match = matchSessionRepository.findMatchSessionAfterChooseGame(userId);

        if (isDateTimeValid(command)) {

            user.setState(PassiveState.DB_COL_NAME);
            Date formattedDate = dateTimeFormatter.parse(command);
            match.setGameTime(formattedDate);
            
            matchSessionRepository.save(match);
            lineUserRepository.save(user);
            NotifyUser notifyUser = new NotifyUser();
            notifyUser.setNewMatchSession(match);

            return Messages.createMatchSuccess(match);
        } else {
            return Messages.CHOOSE_TIME_WRONG_COMMAND;
        }
    }
}