package com.twitteranalyser.twitter.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import twitter4j.Status;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@NodeEntity
public class User implements Serializable {

    @GraphId
    private Long nodeId;
    private String name;
    @Getter
    private String screenName;
    private String location;
    private String description;
    private int followersCount;
    private Status status;
    private int friendsCount;
    private int favouritesCount;
    private Date createdAt;
    private String timeZone;
    private String lang;
    @Relationship(type = "INTERESTED_IN", direction = Relationship.OUTGOING)
    private Set<Tweet> tweets;

    public void used(Tweet tweet) {
        if (tweets == null) {
            tweets = new HashSet<>();
        }
        tweets.add(tweet);
    }

    private User(UserBuilder builder) {
        this.name = builder.name;
        this.screenName = builder.screenName;
        this.location = builder.location;
        this.description = builder.description;
        this.followersCount = builder.followersCount;
        this.status = builder.status;
        this.friendsCount = builder.friendsCount;
        this.favouritesCount = builder.favouritesCount;
        this.createdAt = builder.createdAt;
        this.timeZone = builder.timeZone;
        this.lang = builder.lang;
    }

    public User(Status status) {
        twitter4j.User user = status.getUser();
        this.screenName = user.getName();
        this.screenName = user.getScreenName();
        this.location = user.getLocation();
        this.description = user.getDescription();
        this.followersCount = user.getFollowersCount();
        this.status = user.getStatus();
        this.friendsCount = user.getFollowersCount();
        this.favouritesCount = user.getFavouritesCount();
        this.createdAt = user.getCreatedAt();
        this.timeZone = user.getTimeZone();
        this.lang = user.getLang();
    }

    public User(String screenName) {
        this.screenName = screenName;
    }

    public static class UserBuilder {

        private String name;
        private String screenName;
        private String location;
        private String description;
        private int followersCount;
        private Status status;
        private int friendsCount;
        private int favouritesCount;
        private Date createdAt;
        private String timeZone;
        private String lang;

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder screenName(String screenName) {
            this.screenName = screenName;
            return this;
        }

        public UserBuilder location(String location) {
            this.location = location;
            return this;
        }

        public UserBuilder description(String description) {
            this.description = description;
            return this;
        }

        public UserBuilder followersCount(int followersCount) {
            this.followersCount = followersCount;
            return this;
        }

        public UserBuilder status(Status status) {
            this.status = status;
            return this;
        }

        public UserBuilder friendsCount(int friendsCount) {
            this.friendsCount = friendsCount;
            return this;
        }

        public UserBuilder favouritesCount(int favouritesCount) {
            this.favouritesCount = favouritesCount;
            return this;
        }

        public UserBuilder createdAt(Date createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UserBuilder timeZone(String timeZone) {
            this.timeZone = timeZone;
            return this;
        }

        public UserBuilder lang(String lang) {
            this.lang = lang;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

}
