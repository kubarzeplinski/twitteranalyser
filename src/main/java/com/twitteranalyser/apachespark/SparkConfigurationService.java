package com.twitteranalyser.apachespark;

import com.twitteranalyser.twitter.credentials.TwitterCredentials;
import org.apache.spark.SparkConf;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class SparkConfigurationService {

    public SparkConf setDefaultConfiguration() {
        TwitterCredentials credentials = getTwitterDefaultCredentials();
        setSystemProperties(credentials);
        return new SparkConf().setMaster("local[2]").setAppName(credentials.getApplicationName());
    }

    private TwitterCredentials getTwitterDefaultCredentials() {
        ApplicationContext context = new FileSystemXmlApplicationContext("src/main/java/com/twitteranalyser/apachespark/credentials.xml");
        return (TwitterCredentials) context.getBean("twitterCredentials");
    }

    public SparkConf setCustomConfiguration(TwitterCredentials credentials) {
        credentials.validateProperties(
                credentials.getConsumerKey(),
                credentials.getConsumerSecret(),
                credentials.getAccessToken(),
                credentials.getAccessTokenSecret()
        );
        setSystemProperties(credentials);
        return new SparkConf().setMaster("local[2]").setAppName(credentials.getApplicationName());
    }

    private void setSystemProperties(TwitterCredentials credentials) {
        System.setProperty("twitter4j.oauth.consumerKey", credentials.getConsumerKey());
        System.setProperty("twitter4j.oauth.consumerSecret", credentials.getConsumerSecret());
        System.setProperty("twitter4j.oauth.accessToken", credentials.getAccessToken());
        System.setProperty("twitter4j.oauth.accessTokenSecret", credentials.getAccessTokenSecret());
    }

}
