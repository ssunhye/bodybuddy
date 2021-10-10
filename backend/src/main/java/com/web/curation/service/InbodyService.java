package com.web.curation.service;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.exercise.*;
import com.web.curation.repo.exercise.InbodyRepo;
import com.web.curation.repo.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InbodyService {
    @Autowired
    private InbodyRepo inbodyRepo;

    @Autowired
    private UserRepo userRepo;

    public Inbody inbodyInfo(Long userNo, Long inbodyNo)
    {
        return inbodyRepo.findInbodyByInbodyNoAndUserNo(userNo, inbodyNo);
    }

    public List<Inbody> inbodyInfo(Long userNo) {
        final List<Inbody> result = inbodyRepo.findByUserNoOrderByDateAsc(userNo);

        return result;
    }

    public void updateInbody(Inbody inbody)
    {
        inbodyRepo.save(inbody);
    }

    public BasicResponse saveInbody(Long userNo, InbodyRequest request) {
        final BasicResponse result = new BasicResponse();

        Inbody inbody = new Inbody(request.getWeight(), request.getFat(), request.getMuscle(), request.getBmi(), request.getScore(), request.getDate());
        inbody.setUser(userRepo.findById(userNo).get());

        if (inbodyRepo.save(inbody) != null) {
            result.status = true;
            result.data = "success";
        } else {
            result.status = false;
            result.data = "fail";
        }

        return result;
    }

    public BasicResponse deleteInbody(Long inbodyNo) {
        final BasicResponse result = new BasicResponse();

        inbodyRepo.deleteById(inbodyNo);

        result.status = true;
        result.data = "success";

        return result;
    }

    public BasicResponse updateInbody(Long inbodyNo, InbodyRequest request) {
        final BasicResponse result = new BasicResponse();

        Inbody inbody = inbodyRepo.findById(inbodyNo).get();

        inbody.setBmi(request.getBmi());
        inbody.setFat(request.getFat());
        inbody.setMuscle(request.getMuscle());
        inbody.setScore(request.getScore());
        inbody.setWeight(request.getWeight());

        if (inbodyRepo.save(inbody) != null) {
            result.status = true;
            result.data = "success";
        } else {
            result.status = false;
            result.data = "fail";
        }

        return result;
    }
}
