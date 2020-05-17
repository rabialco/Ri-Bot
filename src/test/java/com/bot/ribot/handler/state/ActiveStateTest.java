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

public class ActiveStateTest {
    @InjectMocks
    private ActiveState activeState;

    private String responses;

    @Mock
    LineUserRepository lineUserRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        LineUser userSalman = new LineUser("1", "Salman");
        userSalman.setState(ActiveState.DB_COL_NAME);
        when(lineUserRepository.findLineUserByUserId("1")).thenReturn(
            userSalman
        );
    }

    @Test
    public void contextLoads() {
        assertThat(activeState).isNotNull();
    }

    @Test
    public void registerTest() {
        responses = Messages.ALREADY_REGISTERED;
        assertEquals(responses, activeState.register("210", "Qra"));
    }

    @Test
    public void makeSessionTest() {
        StringBuilder messages = new StringBuilder();
        messages.append("Silahkan pilih game yang ingin anda mainkan :");
        for(String game : Messages.availableGame){
            messages.append("\n");
            messages.append(game);
        }

        responses = messages.toString();
        assertEquals(responses, activeState.makeSession("1"));
    }

    @Test
    public void othersTest() {
        responses = Messages.ACTIVE_STATE_WRONG_COMMAND;
        assertEquals(responses, activeState.others("1", "hancurkan"));
    }

}