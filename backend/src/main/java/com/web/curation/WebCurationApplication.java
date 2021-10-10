package com.web.curation;

import com.web.curation.repo.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebCurationApplication {
    @Autowired
    private UserRepo repository;

    public static void main(String[] args) {
        SpringApplication.run(WebCurationApplication.class, args);
    }

}