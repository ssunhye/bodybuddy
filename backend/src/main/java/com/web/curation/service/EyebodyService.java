package com.web.curation.service;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.challenge.Challenge;
import com.web.curation.model.challenge.ChallengeDetail;
import com.web.curation.model.challenge.ChallengeRequest;
import com.web.curation.model.exercise.Eyebody;
import com.web.curation.model.exercise.EyebodyRequest;
import com.web.curation.repo.challenge.ChallengeDetailRepo;
import com.web.curation.repo.challenge.ChallengeRepo;
import com.web.curation.repo.exercise.EyebodyRepo;
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
public class EyebodyService {
    @Autowired
    private EyebodyRepo eyebodyRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    S3FileUploadService S3;

    public List<Eyebody> eyebodyInfo(Long userNo) {
        return eyebodyRepo.findByUserNoOrderByDateDesc(userNo);
    }

    public BasicResponse saveEyebody(EyebodyRequest request, MultipartFile file) {
        final BasicResponse response = new BasicResponse();
        LocalDateTime cur_time = LocalDateTime.now();

        // 시간과 originalFilename으로 매핑 시켜서 src 주소를 만들어 낸다.
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


        Eyebody eyebody = new Eyebody(request.getImg(), request.getDate());
        eyebody.setImg(("https://bodybuddy.s3.ap-northeast-2.amazonaws.com/exercise/" + sb.toString()).replaceAll(" ", ""));
        S3.upload(file, "exercise/"+ sb.toString());

        System.out.println(request.getUserNo());
        eyebody.setUser(userRepo.findById(request.getUserNo()).get());

        if (eyebodyRepo.save(eyebody) != null) {
            response.status = true;
            response.data = "success";
        } else {
            response.status = false;
            response.data = "fail";
        }
        return response;
    }
}
