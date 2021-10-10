// package com.web.curation.service;

// import com.web.curation.model.user.Follow;
// import com.web.curation.repo.user.FollowRepo;
// import com.web.curation.repo.user.UserRepo;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// @Service
// public class FollowService {

//     @Autowired
//     FollowRepo followRepo;

//     @Autowired
//     UserRepo userRepo;

//     // 팔로우
//     public void save(int login_id, int page_id) {
//         Follow f = new Follow();
//         f.setFollowing(userRepo.findById(login_id));
//         f.setFollower(userRepo.findById(page_id));
//         followRepo.save(f);
//     }

//     // 언팔로우
//     public void deleteByFollowingIdAndFollowerId(int id1, int id2) {
//         followRepo.deleteByFollowingIdAndFollowerId(id2, id1);
//     }

//     // 팔로우가 되어있는지 확인
//     public boolean find(int id, String userId) {
//         return followRepo.countByFollowerIdAndFollowingUserId(id, userId) != 0;
//     }
// }
