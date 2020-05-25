package com.bot.ribot.handler.state;

import com.bot.ribot.handler.message.Messages;
import com.bot.ribot.handler.observer.NotifyUser;
import com.bot.ribot.model.LineUser;
import com.bot.ribot.model.MatchSession;
import com.bot.ribot.repository.LineUserRepository;
import com.bot.ribot.repository.MatchSessionRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ChooseTimeStateTest {
    @InjectMocks
    private ChooseTimeState chooseTimeState;

    private String responses;

    @Mock
    LineUserRepository lineUserRepository;

    @Mock
    MatchSessionRepository matchSessionRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        LineUser userQra = new LineUser("<3", "Qra");
        MatchSession match = new MatchSession(userQra, "Tinju");
        List<LineUser> myList = new ArrayList<LineUser>();
        userQra.setState(ChooseTimeState.DB_COL_NAME);
        when(lineUserRepository.findLineUserByUserId("<3")).thenReturn(
                userQra
        );
        when(lineUserRepository.findAllLineUser()).thenReturn(
            myList
        );
        when(matchSessionRepository.findMatchSessionAfterChooseGame("<3")).thenReturn(
                match
        );
    }

    @Test
    public void contextLoads() {
        assertThat(chooseTimeState).isNotNull();
    }

    @Test
    public void registerTest() {
        responses = Messages.ALREADY_REGISTERED;
        assertEquals(responses, chooseTimeState.register("210", "Qra"));
    }

    @Test
    public void makeSessionTest() {
        responses = Messages.CHOOSE_TIME_WRONG_COMMAND;
        assertEquals(responses, chooseTimeState.makeSession("1810"));
    }

    // Activate again when notifyUser test is ready
    //  @Test
    //  public void othersTest() throws Exception {
    //      responses = Messages.CHOOSE_TIME_SUCCESS;
    //      assertEquals(responses, chooseTimeState.others("<3", "14-12-2020 13:00"));
    //  }
}