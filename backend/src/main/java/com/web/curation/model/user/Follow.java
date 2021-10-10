// package com.web.curation.model.user;

// import lombok.Data;

// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;
// import javax.persistence.Entity;
// import javax.persistence.Id;


// @Data
// @Entity
// @org.hibernate.annotations.DynamicUpdate
// public class Follow {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     int follow_no;
//     @ManyToOne
//     @JoinColumn(name = "following_user_no", referencedColumnName = "user_no")
//     User following;
//     @ManyToOne
//     @JoinColumn(name = "follow_user_no", referencedColumnName = "user_no")
//     User follower;

//     public void setFollowing(User following) {
//         this.following = following;
//     }

//     public void setFollower(User follower) {
//         this.follower = follower;
//     }
// }