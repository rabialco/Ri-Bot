package com.bot.ribot.model;

import com.bot.ribot.handler.state.ActiveState;
import com.bot.ribot.handler.state.UnregisteredState;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "line_user")
public class LineUser extends AuditModel {
    @Id
    @Column(name = "user_id", unique = true, nullable = false)
    private String userId;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "state")
    private String state = UnregisteredState.DB_COL_NAME;

    public LineUser() {
    }

    /**
     * Constructor for LineUser.
     */
    public LineUser(String userId, String displayName) {
        this.userId = userId;
        this.displayName = displayName;
        this.state = ActiveState.DB_COL_NAME;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String userName) {
        this.displayName = userName;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String settingDebtState) {
        this.state = settingDebtState;
    }
}