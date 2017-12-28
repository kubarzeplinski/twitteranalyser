package com.twitteranalyser.twitter.repository;

import com.twitteranalyser.twitter.model.User;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface UserRepository extends GraphRepository<User> {

    User findByScreenName(String screenName);

}
