package com.web.curation.service;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.user.SignupRequest;
import com.web.curation.model.user.User;
import com.web.curation.repo.user.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public BasicResponse insertUser(SignupRequest request) {
        final BasicResponse result = new BasicResponse();

        User user = new User(request.getUserId(), passwordEncoder.encode(request.getUserPw()), request.getUserEmail(), request.getUserName(),request.getUserImg());

        if (userRepo.save(user) != null) {
            result.status = true;
            result.data = "success";
        } else {
            result.status = false;
            result.data = "fail";
        }

        return result;
    }
}
