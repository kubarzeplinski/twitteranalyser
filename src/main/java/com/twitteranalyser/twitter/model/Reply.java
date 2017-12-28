package com.twitteranalyser.twitter.model;

import org.neo4j.ogm.annotation.*;
import twitter4j.GeoLocation;
import twitter4j.User;

@RelationshipEntity(type="IN_DISCUSSION_WITH")
public class Reply {

    @GraphId
    Long nodeId;
    String text;
    GeoLocation geoLocation;
    @StartNode
    User commentator;
    @EndNode
    User user;

}
