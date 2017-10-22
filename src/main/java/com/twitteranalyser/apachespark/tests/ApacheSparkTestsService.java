package com.twitteranalyser.apachespark.tests;

import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.twitter.TwitterUtils;
import org.springframework.stereotype.Service;
import twitter4j.Status;

@Service
public class ApacheSparkTestsService {

    public void testMethod() {
        final String consumerKey = "ldH2JTJtrEyvuSDKRsrom5VFm";
        final String consumerSecret = "GT0D2iafQMLQCcKaU1MoE9C6rZA5puIpi18i4SBUTAPclrl0Cd";
        final String accessToken = "875731293761396736-WbQcTDI1Lc8MEr2qX7FHUOkvxY14oGi";
        final String accessTokenSecret = "QGcl0tFmCCpcUOqIBCDigeOfsPlZv134V12IiPntwIrMV";

        SparkConf conf = new SparkConf().setMaster("local[2]").setAppName("ToffeeMasterThesis");
        JavaStreamingContext jssc = new JavaStreamingContext(conf, new Duration(10000));

        System.setProperty("twitter4j.oauth.consumerKey", consumerKey);
        System.setProperty("twitter4j.oauth.consumerSecret", consumerSecret);
        System.setProperty("twitter4j.oauth.accessToken", accessToken);
        System.setProperty("twitter4j.oauth.accessTokenSecret", accessTokenSecret);

        JavaReceiverInputDStream<Status> twitterStream = TwitterUtils.createStream(jssc);

        JavaDStream<Status> tweets = twitterStream.filter(status -> true);

        JavaDStream<String> statuses = tweets.map(status ->
                "name: " + status.getUser().getName() + " date: " + status.getCreatedAt() + " text: " + status.getText());

        statuses.print();
        jssc.start();
    }

}
