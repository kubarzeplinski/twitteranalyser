package com.twitteranalyser.apachespark;

import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.twitter.TwitterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.Status;

@Service
public class SparkService {

    @Autowired
    private SparkConfigurationService sparkConfigurationService;

    private JavaStreamingContext javaStreamingContext;

    public JavaReceiverInputDStream<Status> start() {
        SparkConf configuration = sparkConfigurationService.setConfiguration();
        javaStreamingContext = new JavaStreamingContext(configuration, new Duration(10000)); //TODO user should have possibilty to set duration on UI
        return TwitterUtils.createStream(javaStreamingContext);
    }

    public void stop() {
        javaStreamingContext.stop();
    }

}
