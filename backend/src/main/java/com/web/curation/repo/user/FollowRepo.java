// package com.web.curation.repo.user;

// import com.web.curation.model.user.Follow;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Modifying;
// import org.springframework.transaction.annotation.Transactional;

// public interface FollowRepo extends JpaRepository<Follow, Integer> {

//         // 팔로우 count
//         int countByFollowerIdAndFollowingUserId(int id, String userId);

//         // 언팔로우
//         @Modifying
//         @Transactional
//         void deleteByFollowingIdAndFollowerId(int id1, int id2);

// }
