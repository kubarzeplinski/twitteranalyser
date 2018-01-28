package com.twitteranalyser.twitter.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NoArgsConstructor
@NodeEntity
public class KeyWord {

    @GraphId
    Long nodeId;
    @Getter
    String name;
    @Setter
    @Relationship(type = "INTERESTED_IN", direction = Relationship.INCOMING)
    private Tweet tweet;

    public KeyWord(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
