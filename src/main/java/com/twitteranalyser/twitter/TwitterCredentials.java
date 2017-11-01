package com.twitteranalyser.twitter;

import lombok.Getter;

public class TwitterCredentials {

    @Getter
    private String applicationName;
    private String consumerKey;
    private String consumerSecret;
    private String accessToken;
    private String accessTokenSecret;

    public TwitterCredentials(String applicationName, String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret) {
        this.applicationName = applicationName;
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.accessToken = accessToken;
        this.accessTokenSecret = accessTokenSecret;
        init();
    }

    private void init() {
        System.setProperty("twitter4j.oauth.consumerKey", consumerKey);
        System.setProperty("twitter4j.oauth.consumerSecret", consumerSecret);
        System.setProperty("twitter4j.oauth.accessToken", accessToken);
        System.setProperty("twitter4j.oauth.accessTokenSecret", accessTokenSecret);
    }

}
