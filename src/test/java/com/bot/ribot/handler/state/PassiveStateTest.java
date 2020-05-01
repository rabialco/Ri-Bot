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

public class PassiveStateTest {
    @InjectMocks
    private PassiveState passiveState;

    private String responses;

    @Mock
    LineUserRepository lineUserRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        LineUser userQra = new LineUser("1", "Iqra");
        userQra.setState(PassiveState.DB_COL_NAME);
        when(lineUserRepository.findLineUserByUserId("1")).thenReturn(
            userQra
        );
    }

    @Test
    public void contextLoads() {
        assertThat(passiveState).isNotNull();
    }

    @Test
    public void registerTest() {
        responses = Messages.ALREADY_REGISTERED;
        assertEquals(responses, passiveState.register("512", "Iqra"));
    }

    @Test
    public void makeSessionTest() {
        responses = Messages.PASSIVE_STATE_WRONG_COMMAND;
        assertEquals(responses, passiveState.makeSession("1810"));
    }

    @Test
    public void othersTest() {
        responses = Messages.PASSIVE_STATE_SUCCESS;
        assertEquals(responses, passiveState.others("1", "/finish"));

        responses = Messages.PASSIVE_STATE_WRONG_COMMAND;
        assertEquals(responses, passiveState.others("1", "Salman Ganteng"));
    }
}