package com.web.curation.service;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.exercise.*;
import com.web.curation.repo.exercise.PowerliftingRepo;
import com.web.curation.repo.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PowerliftingService {
    @Autowired
    private PowerliftingRepo plRepo;

    @Autowired
    private UserRepo userRepo;

    public List<Powerlifting> powerliftingInfo(Long userNo) {
        List<Powerlifting> powerliftings = plRepo.findByUserNoOrderByDateAsc(userNo);

        return powerliftings;
    }

    public BasicResponse savePowerlifting(PowerliftingRequest request) {
        final BasicResponse result = new BasicResponse();

        Powerlifting powerlifting = new Powerlifting(request.getSquat(), request.getBenchpress(), request.getDeadlift(), request.getDate());
        powerlifting.setUser(userRepo.findById(request.getUserNo()).get());

        if (plRepo.save(powerlifting) != null) {
            result.status = true;
            result.data = "success";
        } else {
            result.status = false;
            result.data = "fail";
        }

        return result;
    }

    public BasicResponse deletePowerlifting(Long plNo) {
        final BasicResponse result = new BasicResponse();

        plRepo.deleteById(plNo);

        result.status = true;
        result.data = "success";

        return result;
    }

    public BasicResponse updatePowerlifting(Long plNo, PowerliftingRequest request) {
        final BasicResponse result = new BasicResponse();

        Powerlifting powerlifting = plRepo.findById(plNo).get();

        powerlifting.setBenchpress(request.getBenchpress());
        powerlifting.setDeadlift(request.getDeadlift());
        powerlifting.setSquat(request.getSquat());

        if (plRepo.save(powerlifting) != null) {
            result.status = true;
            result.data = "success";
        } else {
            result.status = false;
            result.data = "fail";
        }

        return result;
    }
}
