package com.bot.ribot.repository;

import com.bot.ribot.model.MatchSession;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MatchSessionRepository extends JpaRepository<MatchSession, String> {
    @Query(value = "SELECT * FROM LINE_USER LU, MATCH_SESSION MS WHERE USER_ID = ?1"
            + "AND MS.USERFINDER_USER_ID = LU.USER_ID", nativeQuery = true)
    List<MatchSession> findLineUserByFinderUserId(String userID);

    @Query(value = "SELECT * FROM LINE_USER LU, MATCH_SESSION MS WHERE USER_ID = ?1"
            + "AND MS.USERRIVAL_USER_ID = LU.USER_ID", nativeQuery = true)
    List<MatchSession> findLineUserByRivalId(String userID);

    @Query(value = "SELECT * FROM MATCH_SESSION WHERE MATCH_ID = ?1 ", nativeQuery = true)
    MatchSession findSessionByMatchId(long matchId);

    @Query(value = "SELECT state FROM LINE_USER WHERE USER_ID = ?1", nativeQuery = true)
    String findStateById(String userID);
}
