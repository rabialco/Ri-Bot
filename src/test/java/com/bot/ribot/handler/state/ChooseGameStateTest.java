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

public class ChooseGameStateTest {
    @InjectMocks
    private ChooseGameState chooseGameState;

    private String responses;

    @Mock
    LineUserRepository lineUserRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        LineUser userQra = new LineUser("<3", "Qra");
        userQra.setState(ChooseGameState.DB_COL_NAME);
        when(lineUserRepository.findLineUserByUserId("<3")).thenReturn(
            userQra
        );
    }
    
    @Test
    public void contextLoads() {
        assertThat(chooseGameState).isNotNull();
    }

    @Test
    public void registerTest() {
        responses = Messages.ALREADY_REGISTERED;
        assertEquals(responses, chooseGameState.register("210", "Qra"));
    }

    @Test
    public void makeSessionTest() {
        responses = Messages.CHOOSE_GAME_WRONG_COMMAND;
        assertEquals(responses, chooseGameState.makeSession("1810"));
    }

    @Test
    public void othersTest() {
        responses = Messages.CHOOSE_GAME_SUCCESS;
        assertEquals(responses, chooseGameState.others("<3", "Tinju"));
    }
}