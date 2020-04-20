package com.bot.ribot.repository;

import com.bot.ribot.model.LineUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public class LineUserRepository extends JpaRepository<LineUser, String> {

}