package com.web.curation.controller.exercise;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.exercise.EyebodyRequest;
import com.web.curation.service.EyebodyService;
import com.web.curation.service.S3FileUploadService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@RestController
@RequestMapping("/eyebody")
public class EyebodyController {
    @Autowired
    EyebodyService eyebodyService;

    @Autowired
    S3FileUploadService S3;

    @GetMapping("/{userNo}")
    @ApiOperation(value = "사용자 눈바디 정보 반환")
    public Object eyebodyInfo(@RequestParam(required = true) final Long userNo) {
        return new ResponseEntity<>(eyebodyService.eyebodyInfo(userNo), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "사용자 눈바디 정보 등록")
    public Object saveChallenge(@Valid @RequestParam String date,
                                @Valid @RequestParam Long userNo,
                                @Valid @RequestParam("image") MultipartFile file) {

        EyebodyRequest request = new EyebodyRequest("", LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE), userNo);
        return new ResponseEntity<>(eyebodyService.saveEyebody(request, file), HttpStatus.OK);
    }
}
