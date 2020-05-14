package com.bot.ribot.repository;

//import com.bot.ribot.model.LineUser;
//import com.bot.ribot.model.MatchSession;
//import org.junit.Before;
//import org.junit.runner.RunWith;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.batch.JpaBatchConfigurer;
//import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.junit.jupiter.api.Test;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.sql.Time;


//@RunWith(SpringRunner.class)
//@ContextConfiguration(classes= {JpaBaseConfiguration.class, TestEntityManager.class,
//        MatchSessionRepository.class})
//@DataJpaTest

public class MatchSessionTest {
    // To Be Implemented
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Autowired
//    private MatchSessionRepository matchSessionRepo;
//
//    @Test
//    public void findLineUserByFinderUserId() {
//        // given
//        LineUser userFinder = new LineUser("1", "rabi");
//        entityManager.persist(userFinder);
//        LineUser userRival = new LineUser("2", "alco");
//        entityManager.persist(userRival);
//        String gamePlace = "Rumah";
//        String gameType = "Tenis Meja";
//        Time gameTime = new Time(System.currentTimeMillis());
//        MatchSession matchSession = new MatchSession(userFinder, userRival,
//                gameTime, gamePlace, gameType);
//        entityManager.merge(matchSession);
//        entityManager.flush();
//
//        MatchSession found = matchSessionRepo.
//                findLineUserByFinderUserId(userFinder.getUserId());
//
//        assertThat(found.getGameType())
//                .isEqualTo(matchSession.getGameType());
//    }
//
//    @Test
//    public void findLineUserByRivalId() {
//        // To Be Implement
//        LineUser userFinder = new LineUser("1", "rabi");
//        entityManager.persist(userFinder);
//        LineUser userRival = new LineUser("2", "alco");
//        entityManager.persist(userRival);
//        String gamePlace = "Rumah";
//        String gameType = "Tenis Meja";
//        Time gameTime = new Time(System.currentTimeMillis());
//        MatchSession matchSession = new MatchSession(userFinder, userRival,
//                gameTime, gamePlace, gameType);
//        entityManager.merge(matchSession);
//        entityManager.flush();
//
//        MatchSession found = matchSessionRepo.
//                findLineUserByFinderUserId(userRival.getUserId());
//
//        assertThat(found.getGameType())
//                .isEqualTo(matchSession.getGameType());
//    }

}
