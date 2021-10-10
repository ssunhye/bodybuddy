package com.web.curation.service;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.challenge.*;
import com.web.curation.repo.challenge.ChallengeDetailRepo;
import com.web.curation.repo.challenge.ChallengeRepo;
import com.web.curation.repo.challenge.ChallengeLikeRepo;
import com.web.curation.repo.challenge.ChallengeScoreRepo;
import com.web.curation.repo.user.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Slf4j
public class ChallengeService {
    @Autowired
    private ChallengeRepo challengeRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ChallengeDetailRepo challengeDetailRepo;

    @Autowired
    private ChallengeLikeRepo challengeLikeRepo;

    @Autowired
    private ChallengeScoreRepo challengeScoreRepo;

    @Autowired
    S3FileUploadService S3;

    public List<Challenge> challengeList() {
        return challengeRepo.findAll();
    }

    public Page<Challenge> challengePageList(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Challenge> challengePages = challengeRepo.findAll(pageable);

        return challengePages;
    }

    public List<Challenge> myChallengeList(Long userNo) {
        return challengeRepo.findByUserNo(userNo);
    }

    public List<ChallengeLike> myChallengeLikeList(Long chlgNo) {
        return challengeLikeRepo.findByChlgNo(chlgNo);
    }

    public List<ChallengeScore> myChallengeScoreList(Long chlgDetailNo) {
        return challengeScoreRepo.findByChlgDetailNo(chlgDetailNo);
    }

    public Challenge challengeInfo(Long chlgNo) {
        return challengeRepo.findByChlgNo(chlgNo);
    }

    public List<ChallengeDetail> challengeDetailInfo(Long chlgNo) {
        return challengeDetailRepo.findByChlgNoOrderByScoreDesc(chlgNo);
    }
    public Long challengeLikeUp(Long chlgNo) {
        Challenge challenge = challengeRepo.findById(chlgNo).get();
        challenge.setLikeCnt(challenge.getLikeCnt()+1);
        challengeRepo.save(challenge);
        return challenge.getLikeCnt();
    }

    public Long challengeLikeDown(Long chlgNo) {
        Challenge challenge = challengeRepo.findById(chlgNo).get();
        challenge.setLikeCnt(challenge.getLikeCnt()-1);
        challengeRepo.save(challenge);
        return challenge.getLikeCnt();
    }

    public Long challengeDetailScoreUp(Long chlgNo, Long chlgDetailNo) {
        ChallengeDetail challengeDetail = challengeDetailRepo.findById(chlgDetailNo).get();
        challengeDetail.setScore(challengeDetail.getScore()+1);
        challengeDetailRepo.save(challengeDetail);
        return challengeDetail.getScore();
    }

    public Long challengeDetailScoreDown(Long chlgNo, Long chlgDetailNo) {
        ChallengeDetail challengeDetail = challengeDetailRepo.findById(chlgDetailNo).get();
        challengeDetail.setScore(challengeDetail.getScore()-1);
        challengeDetailRepo.save(challengeDetail);
        return challengeDetail.getScore();
    }

    public BasicResponse saveChallenge(ChallengeRequest request, MultipartFile file) {
        final BasicResponse response = new BasicResponse();
        LocalDateTime cur_time = LocalDateTime.now();

        // 시간과 originalFilename으로 ㅍ매핑 시켜서 src 주소를 만들어 낸다.
        //Date date = new Date();
        StringBuilder sb = new StringBuilder();

        if(file.isEmpty()) {
            sb.append("none");
        }
        else
        {
            sb.append(cur_time);
            sb.append("no" + request.getUserNo());
            sb.append(file.getOriginalFilename());
        }


        Challenge challenge = new Challenge(request.getTitle(), request.getContent(), request.getImg(), request.getStartDate(), request.getEndDate());
        challenge.setImg(("https://bodybuddy.s3.ap-northeast-2.amazonaws.com/challenge/" + sb.toString()).replaceAll(" ", ""));
        S3.upload(file, "challenge/"+ sb.toString());

        System.out.println(request.getUserNo());
        challenge.setUser(userRepo.findById(request.getUserNo()).get());

        if (challengeRepo.save(challenge) != null) {
            response.status = true;
            response.data = "success";
        } else {
            response.status = false;
            response.data = "fail";
        }
        return response;
    }

    public BasicResponse joinChallenge(JoinChallengeRequest request, MultipartFile file) {
        final BasicResponse result = new BasicResponse();
        LocalDateTime cur_time = LocalDateTime.now();

        StringBuilder sb = new StringBuilder();

        if(file.isEmpty()) {
            sb.append("none");
        }
        else
        {
            sb.append(cur_time);
            sb.append("no" + request.getUserNo());
            sb.append(file.getOriginalFilename());
        }

        ChallengeDetail challengeDetail = new ChallengeDetail(request.getImg());
        S3.upload(file, "challenge/"+ sb.toString());
        challengeDetail.setImg(("https://bodybuddy.s3.ap-northeast-2.amazonaws.com/challenge/" + sb.toString()).replaceAll(" ", ""));

        challengeDetail.setUser(userRepo.findById(request.getUserNo()).get());
        challengeDetail.setChallenge(challengeRepo.findById(request.getChlgNo()).get());
        challengeDetail.setScore(0L);

        Challenge challenge = challengeRepo.findById(request.getChlgNo()).get();
        challenge.setLikeCnt(challenge.getLikeCnt()+1);
        challengeRepo.save(challenge);

        if (challengeDetailRepo.save(challengeDetail) != null) {
            result.status = true;
            result.data = "success";
        } else {
            result.status = false;
            result.data = "fail";
        }

        return result;
    }
}
