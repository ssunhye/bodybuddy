package com.web.curation.repo.feed;


import com.web.curation.model.feed.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Long> {
}