package com.twitteranalyser.apachespark;

import com.twitteranalyser.twitter.TwitterCredentials;
import org.apache.spark.SparkConf;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class SparkConfigurationService {

    public SparkConf setConfiguration() {
        TwitterCredentials twitterCredentials = getTwitterCredentials();
        return new SparkConf().setMaster("local[2]").setAppName(twitterCredentials.getApplicationName());
    }

    private TwitterCredentials getTwitterCredentials() {
        ApplicationContext context = new FileSystemXmlApplicationContext("src/main/java/com/twitteranalyser/apachespark/credentials.xml");
        return (TwitterCredentials) context.getBean("twitterCredentials");
    }

}
