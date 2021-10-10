package com.web.curation.controller.exercise;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.exercise.PowerliftingRequest;
import com.web.curation.service.PowerliftingService;
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
@RequestMapping("/powerlifting")
public class PowerliftingController {
    @Autowired
    PowerliftingService plService;

    @GetMapping("/{userNo}")
    @ApiOperation(value = "사용자 가장 최근 파워리프팅 정보 반환")
    public Object powerliftingInfo(@RequestParam(required = true) final Long userNo) {
        return new ResponseEntity<>(plService.powerliftingInfo(userNo), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "사용자 파워리프팅 정보 등록")
    public Object savePowerlifting(@Valid @RequestBody PowerliftingRequest request) {
        return new ResponseEntity<>(plService.savePowerlifting(request), HttpStatus.OK);
    }

    @DeleteMapping("/{plNo}")
    @ApiOperation(value = "사용자 파워리프팅 정보 삭제")
    public Object deletePowerlifting(@RequestParam(required = true) final Long plNo) {
        return new ResponseEntity<>(plService.deletePowerlifting(plNo), HttpStatus.OK);
    }

    @PutMapping("/{plNo}")
    @ApiOperation(value = "사용자 파워리프팅 정보 수정")
    public Object updatePowerlifting(@RequestParam(required = true) final Long plNo, @Valid @RequestBody PowerliftingRequest request) {
        return new ResponseEntity<>(plService.updatePowerlifting(plNo, request), HttpStatus.OK);
    }
}