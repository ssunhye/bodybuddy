package com.web.curation.repo.user;

import com.web.curation.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByUserEmail(String userEmail);
    User findUserByUserId(String userId);
    User findUserByUserName(String userName);
    Optional<User> findByUserId(String userId);

    @Transactional
    Integer removeUserByUserId(String userId);
}