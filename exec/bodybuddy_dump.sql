create database bodybuddy; 
use bodybuddy;

CREATE TABLE `powerlifting` (
  `pl_no` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_no` bigint(20) NOT NULL,
  `squat` int(11) DEFAULT NULL,
  `benchpress` int(11) DEFAULT NULL,
  `deadlift` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`pl_no`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3;

CREATE TABLE `user` (
  `user_no` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `user_id` varchar(20) NOT NULL,
  `user_email` varchar(20) DEFAULT NULL,
  `user_pw` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT current_timestamp(),
  `user_img` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`user_no`),
  UNIQUE KEY `user_idx_unique_email` (`user_email`,`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb3;

CREATE TABLE `challenge` (
  `chlg_no` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) DEFAULT NULL,
  `user_no` varchar(20) DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL,
  `like_cnt` bigint(20) DEFAULT 0,
  `img` varchar(500) DEFAULT NULL,
  `cnt` bigint(20) DEFAULT 0,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  PRIMARY KEY (`chlg_no`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb3;

CREATE TABLE `challenge_detail` (
  `chlg_detail_no` bigint(20) NOT NULL AUTO_INCREMENT,
  `chlg_no` bigint(20) NOT NULL,
  `user_no` varchar(20) NOT NULL,
  `img` varchar(500) DEFAULT NULL,
  `score` bigint(20) DEFAULT 0,
  PRIMARY KEY (`chlg_detail_no`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3;

CREATE TABLE `time_record` (
  `time_record_no` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_no` bigint(20) DEFAULT NULL,
  `start` datetime DEFAULT NULL,
  `end` datetime DEFAULT NULL,
  `create_date` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`time_record_no`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

CREATE TABLE `inbody` (
  `inbody_no` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_no` bigint(20) NOT NULL,
  `weight` double DEFAULT NULL,
  `fat` double DEFAULT NULL,
  `muscle` double DEFAULT NULL,
  `bmi` double DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `create_date` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`inbody_no`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;

CREATE TABLE `refresh_token` (
  `user_no` bigint(20) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `expiration_date` datetime DEFAULT NULL,
  PRIMARY KEY (`user_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `eyebody` (
  `eyebody_no` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_no` bigint(20) NOT NULL,
  `img` varchar(100) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`eyebody_no`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;

CREATE TABLE `vote` (
  `vote_no` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_no` bigint(20) NOT NULL,
  `chlg_no` bigint(20) NOT NULL,
  `chlg_detail_no` bigint(20) NOT NULL,
  PRIMARY KEY (`vote_no`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb3;

commit;

