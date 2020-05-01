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

public class UnregisteredStateTest {
    @InjectMocks
    private UnregisteredState unregisteredState;

    private String responses;

    @Mock
    LineUserRepository lineUserRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        LineUser userQra = new LineUser("1", "Iqra");
        userQra.setState(UnregisteredState.DB_COL_NAME);
        when(lineUserRepository.findLineUserByUserId("1")).thenReturn(
            userQra
        );
    }

    @Test
    public void contextLoads() {
        assertThat(unregisteredState).isNotNull();
    }

    @Test
    public void registerTest() {
        responses = Messages.SUCCESSFULLY_REGISTERED;
        assertEquals(responses, unregisteredState.register("1", "Love"));
    }

    @Test
    public void makeSessionTest() {
        responses = Messages.UNREGISTERED_WRONG_COMMAND;
        assertEquals(responses, unregisteredState.makeSession("1"));
    }

    @Test
    public void othersTest() {
        responses = Messages.UNREGISTERED_WRONG_COMMAND;
        assertEquals(responses, unregisteredState.others("1", "Tes"));
    }
}