package com.twitteranalyser.twitter.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;
import twitter4j.GeoLocation;
import twitter4j.Status;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@RelationshipEntity(type="INTERESTED_IN")
public class Tweet implements Serializable {

    Long id;
    String text;
    GeoLocation geoLocation;
    @EndNode
    KeyWord keyWord;
    @StartNode
    User user;
//    String inReplyToScreenName;
//    Status retweetedStatus;

    public Tweet(KeyWord keyWord, User user, Status status) {
        this.keyWord = keyWord;
        this.user = user;
        this.text = status.getText();
        this.geoLocation = status.getGeoLocation();
    }

    public Tweet(KeyWord keyWord, User user, String text) {
        this.keyWord = keyWord;
        this.user = user;
        this.text = text;
    }

}
