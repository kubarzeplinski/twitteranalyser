package com.twitteranalyser.twitter.repository;

import com.twitteranalyser.twitter.model.KeyWord;
import com.twitteranalyser.twitter.model.Tweet;
import com.twitteranalyser.twitter.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Neo4jDatabaseTest {

    @Autowired
    private Session session;

    @Autowired
    KeyWordRepository keyWordRepository;

    @Autowired
    UserRepository userRepository;

    public Neo4jDatabaseTest() {
    }

    @Before
    public void setUp() {
        //End node
        KeyWord keyWord = new KeyWord("FOOTBALL");
        keyWordRepository.save(keyWord);

        //Start node
        User user = new User("Radzio");
        userRepository.save(user);

        //Relationship
        Tweet tweet = new Tweet(keyWord, user, "I like FOOTBALL.");

        //Add relationship to end node
        keyWord.addTweet(tweet);
        keyWordRepository.save(keyWord);
    }

    @Test
    public void testKeyWordRepository() {
        KeyWord football = keyWordRepository.findByName("FOOTBALL");
        assertEquals("FOOTBALL", football.getName());
    }

    @Test
    public void testUserRepository() {
        User radzio = userRepository.findByScreenName("Radzio");
        assertEquals("Radzio", radzio.getScreenName());
    }

}
