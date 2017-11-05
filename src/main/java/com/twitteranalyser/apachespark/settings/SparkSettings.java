package com.twitteranalyser.apachespark.settings;

import com.twitteranalyser.twitter.credentials.TwitterCredentials;
import lombok.Getter;

@Getter
public class SparkSettings {

    long duration;
    TwitterCredentials credentials;

}
