package com.web.curation.controller.exercise;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.exercise.TimeRecordRequest;
import com.web.curation.service.TimeRecordService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@RestController
@RequestMapping("/timeRecord")
public class TimeRecordController {
    @Autowired
    TimeRecordService timeRecordService;

    @GetMapping("/{userNo}")
    @ApiOperation(value = "운동 시간 측정 기록 반환")
    public Object timeRecordInfo(@RequestParam(required = true) final Long userNo) {
        return new ResponseEntity<>(timeRecordService.timeRecordInfo(userNo), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "운동 시간 측정 기록")
    public Object saveTimeRecord(@RequestParam(required = true) final Long userNo, @Valid @RequestBody TimeRecordRequest request) {
        return new ResponseEntity<>(timeRecordService.saveTimeRecord(userNo, request), HttpStatus.OK);
    }

    @DeleteMapping("/{timeRecordNo}")
    @ApiOperation(value = "운동시간 측정 기록 삭제")
    public Object deleteTimeRecord(@RequestParam(required = true) final Long timeRecordNo) {
        return new ResponseEntity<>(timeRecordService.deleteTimeRecord(timeRecordNo), HttpStatus.OK);
    }

    @PutMapping("/{timeRecordNo}")
    @ApiOperation(value = "운동시간 측정 기록 수정")
    public Object updateTimeRecord(@RequestParam(required = true) final Long timeRecordNo, @Valid @RequestBody TimeRecordRequest request) {
        return new ResponseEntity<>(timeRecordService.updateTimeRecord(timeRecordNo, request), HttpStatus.OK);
    }
}