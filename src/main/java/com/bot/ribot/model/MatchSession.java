package com.bot.ribot.model;

import com.bot.ribot.handler.state.ActiveState;
import com.bot.ribot.handler.state.UnregisteredState;
import com.bot.ribot.repository.LineUserRepository;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.beans.factory.annotation.Autowired;


@Entity
@Table(name = "match_session")
public class MatchSession {
    @Id
    @Column(name = "match_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long matchId;

    @Column(name = "game_type")
    private String gameType;

    @Column(name = "game_time")
    private Date gameTime;

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

    @Autowired
    LineUserRepository lineUserRepository;

    public MatchSession() {
    }
    
    /**
     * Constructor for MatchSession.
     */
    public MatchSession(LineUser userFinder, String gameType) {
        this.userFinder = userFinder;
        this.userRival = userFinder;
        this.gameTime = null;
        this.gamePlace = null;
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

    public void setGamePlace(String gamePlace) {
        this.gamePlace = gamePlace;
    }

    public String getGamePlace() {
        return gamePlace;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameTime(Date gameTime) {
        this.gameTime = gameTime;
    }

    public Date getGameTime() {
        return gameTime;
    }

    public Long getMatchId() {
        return matchId;
    }
    
    /**
    * Use to make own print value of matchSession object.
    */
    
    public String toString() {
        String cetak = "";
        
        cetak = cetak.concat("ID match ini adalah : " + matchId + "\n");
        cetak = cetak.concat("Dibuat oleh user : " + userFinder.getDisplayName() + "\n");
        cetak = cetak.concat("Game yang akan dimainkan: " + gameType + "\n");

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        cetak = cetak.concat("Akan dimainkan pada: " + formatter.format(gameTime) + "\n");

        return cetak;
    }

}
