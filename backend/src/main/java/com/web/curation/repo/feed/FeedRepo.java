
package com.web.curation.repo.feed;

import com.web.curation.model.feed.Feed;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface FeedRepo extends MongoRepository<Feed, String> {
    Long deleteFeedByUserId(String userId);
    List<Feed> findByUserId(String userId);
}
