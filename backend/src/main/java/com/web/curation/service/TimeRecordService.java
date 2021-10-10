package com.web.curation.service;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.exercise.*;
import com.web.curation.repo.exercise.TimeRecordRepo;
import com.web.curation.repo.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeRecordService {
    @Autowired
    private TimeRecordRepo timeRecordRepo;

    @Autowired
    private UserRepo userRepo;

    public TimeRecordResponse timeRecordInfo(Long userNo) {
        final TimeRecordResponse result = new TimeRecordResponse();
        List<TimeRecord> records = timeRecordRepo.findAllByUserNo(userNo);

        if(records.isEmpty()) {

        } else {
            result.setStart(records.get(0).getStart());
            result.setEnd(records.get(0).getEnd());
        }

        return result;
    }

    public BasicResponse saveTimeRecord(Long userNo, TimeRecordRequest request) {
        final BasicResponse result = new BasicResponse();

        TimeRecord record = new TimeRecord(request.getStart(), request.getEnd());
        record.setUser(userRepo.findById(userNo).get());

        if (timeRecordRepo.save(record) != null) {
            result.status = true;
            result.data = "success";
        } else {
            result.status = false;
            result.data = "fail";
        }

        return result;
    }

    public BasicResponse deleteTimeRecord(Long timeRecordNo) {
        final BasicResponse result = new BasicResponse();

        timeRecordRepo.deleteById(timeRecordNo);

        result.status = true;
        result.data = "success";

        return result;
    }

    public BasicResponse updateTimeRecord(Long timeRecordNo, TimeRecordRequest request) {
        final BasicResponse result = new BasicResponse();

        TimeRecord record = timeRecordRepo.findById(timeRecordNo).get();

        record.setStart(request.getStart());
        record.setEnd(request.getEnd());

        if (timeRecordRepo.save(record) != null) {
            result.status = true;
            result.data = "success";
        } else {
            result.status = false;
            result.data = "fail";
        }

        return result;
    }
}
