package com.twitteranalyser.twitter.repository;

import com.twitteranalyser.twitter.model.KeyWord;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface KeyWordRepository extends GraphRepository<KeyWord>  {

    KeyWord findByName(String name);

}
