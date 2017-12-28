package com.twitteranalyser.twitter.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@NodeEntity
public class KeyWord implements Serializable {

    @GraphId
    Long nodeId;
    @Getter
    String name;
    @Relationship(type = "INTERESTED_IN", direction = Relationship.INCOMING)
    private Set<Tweet> tweets;

    public KeyWord(String name) {
        this.name = name;
    }

    public void addTweet(Tweet tweet) {
        if (tweets == null) {
            tweets = new HashSet<>();
        }
        tweets.add(tweet);
    }

    @Override
    public String toString() {
        return name;
    }

}
