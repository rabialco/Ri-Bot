package com.bot.ribot.repository;

import com.bot.ribot.model.LineUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LineUserRepository extends JpaRepository<LineUser, String> {
    @Query(value = "SELECT * FROM LINE_USER WHERE USER_ID = ?1", nativeQuery = true)
    LineUser findLineUserByUserId(String userID);

    @Query(value = "SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM LINE_USER c WHERE c.USER_ID = ?1", nativeQuery = true)
    boolean isUserRegistered(String userID);

    @Query(value = "SELECT state FROM LINE_USER WHERE USER_ID = ?1", nativeQuery = true)
    String findStateById(String userID);
}