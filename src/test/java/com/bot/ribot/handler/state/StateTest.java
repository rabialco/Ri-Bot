package com.bot.ribot.handler.state;

import com.bot.ribot.handler.message.Messages;
import com.bot.ribot.model.LineUser;
import com.bot.ribot.repository.LineUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class StateTest {
    @InjectMocks
    private ActiveState activeState;
    @InjectMocks
    private ChooseGameState chooseGameState;
    @InjectMocks
    private ChooseTimeState chooseTimeState;
    @InjectMocks
    private PassiveState passiveState;
    @InjectMocks
    private UnregisteredState unregisteredState;

    private String responses;

    @Mock
    LineUserRepository lineUserRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void contextLoads() {
        assertThat(activeState).isNotNull();
        assertThat(chooseGameState).isNotNull();
        assertThat(chooseTimeState).isNotNull();
        assertThat(passiveState).isNotNull();
        assertThat(unregisteredState).isNotNull();
    }

    @Test
    public void remindRivalTest() {
        responses = Messages.REMIND_SUCCESSFUL;
        assertEquals(responses, activeState.remindRival("1"));
        assertEquals(responses, chooseGameState.remindRival("2"));
        assertEquals(responses, chooseTimeState.remindRival("3"));
        assertEquals(responses, passiveState.remindRival("4"));
        assertEquals(responses, unregisteredState.remindRival("5"));
    }
}