package com.bot.ribot.handler.state.helper;

import com.bot.ribot.handler.state.ActiveState;
import com.bot.ribot.handler.state.ChooseGameState;
import com.bot.ribot.handler.state.ChooseTimeState;
import com.bot.ribot.handler.state.PassiveState;
import com.bot.ribot.handler.state.State;
import com.bot.ribot.handler.state.UnregisteredState;
import com.bot.ribot.repository.LineUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StateHelper {
    @Autowired
    LineUserRepository lineUserRepository;

    @Autowired
    private ActiveState activeState;

    @Autowired
    private ChooseGameState chooseGameState;

    @Autowired
    private ChooseTimeState chooseTimeState;

    @Autowired
    private PassiveState passiveState;

    @Autowired
    private UnregisteredState unregisteredState;

    public State getState(String stateString) {
        switch (stateString) {
            case ActiveState.DB_COL_NAME:
                return activeState;
            case ChooseGameState.DB_COL_NAME:
                return chooseGameState;
            case ChooseTimeState.DB_COL_NAME:
                return chooseTimeState;
            case PassiveState.DB_COL_NAME:
                return passiveState;
            default:
                return activeState;
        }
    }

    public State getUserState(String userId) {
        State state;
        if (lineUserRepository.isUserRegistered(userId)) {
            String stateString = lineUserRepository.findStateById(userId);
            state = getState(stateString);
        } else {
            state = unregisteredState;
        }
        return state;
    }
}