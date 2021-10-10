package com.web.curation.service;

import com.web.curation.model.user.User;
import com.web.curation.repo.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo repository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User user = repository.findByUserEmail(userEmail);
        if(user==null) {
            throw new UsernameNotFoundException("User not found with username: " + userEmail);
        }
        return new org.springframework.security.core.userdetails.User(user.getUserEmail(), user.getUserPw(), new ArrayList<>());
    } 
}