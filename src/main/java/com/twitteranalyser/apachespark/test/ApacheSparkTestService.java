package com.twitteranalyser.apachespark.test;

import com.twitteranalyser.twitter.model.KeyWord;
import com.twitteranalyser.twitter.model.Tweet;
import com.twitteranalyser.twitter.model.User;
import com.twitteranalyser.twitter.repository.KeyWordRepository;
import com.twitteranalyser.twitter.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.twitter.TwitterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.Status;

@Service
public class ApacheSparkTestService {

    @Autowired
    KeyWordRepository keyWordRepository;
    @Autowired
    UserRepository userRepository;

    private KeyWord keyWord;

    public void testMethod() {
        //credentials and appName are specified in xml file
        final String consumerKey = "";
        final String consumerSecret = "";
        final String accessToken = "";
        final String accessTokenSecret = "";

        SparkConf conf = new SparkConf().setMaster("local[2]").setAppName("");
        JavaStreamingContext jssc = new JavaStreamingContext(conf, new Duration(10000));

        System.setProperty("twitter4j.oauth.consumerKey", consumerKey);
        System.setProperty("twitter4j.oauth.consumerSecret", consumerSecret);
        System.setProperty("twitter4j.oauth.accessToken", accessToken);
        System.setProperty("twitter4j.oauth.accessTokenSecret", accessTokenSecret);

        JavaReceiverInputDStream<Status> twitterStream = TwitterUtils.createStream(jssc);

        JavaDStream<Status> tweets = twitterStream.filter(status ->
                StringUtils.containsIgnoreCase(status.getText(), "FOOTBALL")
        );

        JavaDStream<String> statuses = tweets.map(status ->
            "name: " + status.getUser().getName() + " date: " + status.getCreatedAt() + " text: " + status.getText()
        );

        statuses.print();
        jssc.start();
    }

    public void testWithNeo4jMethod() {
        //credentials and appName are specified in xml file
        final String consumerKey = "";
        final String consumerSecret = "";
        final String accessToken = "";
        final String accessTokenSecret = "";

        SparkConf conf = new SparkConf().setMaster("local[2]").setAppName("");
        JavaStreamingContext jssc = new JavaStreamingContext(conf, new Duration(10000));

        System.setProperty("twitter4j.oauth.consumerKey", consumerKey);
        System.setProperty("twitter4j.oauth.consumerSecret", consumerSecret);
        System.setProperty("twitter4j.oauth.accessToken", accessToken);
        System.setProperty("twitter4j.oauth.accessTokenSecret", accessTokenSecret);

        JavaReceiverInputDStream<Status> stream = TwitterUtils.createStream(jssc);

        keyWord = new KeyWord("FOOTBALL");
        keyWordRepository.save(keyWord);

        JavaDStream<Status> tweets = stream.filter(status ->
                StringUtils.containsIgnoreCase(status.getText(), "FOOTBALL")
        );

        JavaDStream<String> statuses = tweets.map(status -> {
            User user = new User(status);
            userRepository.save(user);
            Tweet tweet = new Tweet(keyWord, user, status);
            keyWord.addTweet(tweet);
            keyWordRepository.save(keyWord);
            return "name: " + status.getUser().getName() + " date: " + status.getCreatedAt() + " text: " + status.getText();
        });

        statuses.print();
        jssc.start();
    }

}
