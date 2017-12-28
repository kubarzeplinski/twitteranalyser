package com.twitteranalyser.twitter.model;

import org.neo4j.ogm.annotation.*;
import twitter4j.GeoLocation;
import twitter4j.User;

@RelationshipEntity(type="INTERESTED_IN_TWEET_BY")
public class Retweet {

    @GraphId
    Long nodeId;
    String text;
    GeoLocation geoLocation;
    @StartNode
    User retweeter;
    @EndNode
    User user;

}
