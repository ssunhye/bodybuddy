package com.web.curation.service;
import com.web.curation.model.feed.Feed;
import com.web.curation.repo.feed.FeedRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedService {
    @Autowired
    MongoTemplate mt;

    @Autowired
    FeedRepo feedRepo;

    public Object list(int page)
    {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Feed> feedlist = feedRepo.findAll(pageable);

        return feedlist;
    }

    public Object create(Feed request)
    {
        //System.out.println("save1");
        mt.insert(request);
        //System.out.println("save2");
        return "success";
    }

    public List<Feed> myFeedList(String userId) {
        return feedRepo.findByUserId(userId);
    }

    public void delete(String id) {
        Long temp = feedRepo.deleteFeedByUserId(id);
    }
}
