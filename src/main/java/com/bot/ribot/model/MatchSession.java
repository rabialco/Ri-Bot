package com.bot.ribot.model;

import com.bot.ribot.handler.state.ActiveState;
import com.bot.ribot.handler.state.UnregisteredState;
import java.sql.Time;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "match_session")
public class MatchSession extends AuditModel {
    @Id
    @Column(name = "match_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long matchId;

    @Column(name = "game_type")
    private String gameType;

    @Column(name = "game_time")
    private Time gameTime;

    @Column(name = "game_place")
    private String gamePlace;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "user_id", nullable = false)
    private LineUser userFinder;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "user_id", nullable = false)
    private LineUser userRival;

    @Column(name = "state")
    private String state = UnregisteredState.DB_COL_NAME;

    public MatchSession() {
    }

    /**
     * Constructor for MatchSession.
     */
    public MatchSession(LineUser userFinder, LineUser userRival, Time gameTime,
                        String gamePlace, String gameType) {
        this.userFinder = userFinder;
        this.userRival = userRival;
        this.gameTime = gameTime;
        this.gamePlace = gamePlace;
        this.gameType = gameType;
        this.state = ActiveState.DB_COL_NAME;
    }

    public LineUser getUserIdFinder() {
        return userFinder;
    }

    public LineUser getUserIdRival() {
        return userRival;
    }

    public void setState(String settingDebtState) {
        this.state = settingDebtState;
    }

    public String getState() {
        return this.state;
    }

    public void setUserFinder(LineUser userFinder) {
        this.userFinder = userFinder;
    }

    public LineUser getUserFinder() {
        return userFinder;
    }

    public void setUserRival(LineUser userRival) {
        this.userRival = userRival;
    }

    public LineUser getUserRival() {
        return userRival;
    }

    public void setGame_place(String gamePlace) {
        this.gamePlace = gamePlace;
    }

    public String getGamePlace() {
        return gamePlace;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getGame_type() {
        return gameType;
    }

    public void setGameTime(Time gameTime) {
        this.gameTime = gameTime;
    }

    public Time getGameTime() {
        return gameTime;
    }

    public Long getMatchId() {
        return matchId;
    }

}
