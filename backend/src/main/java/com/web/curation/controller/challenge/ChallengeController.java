package com.web.curation.controller.challenge;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.challenge.ChallengeRequest;
import com.web.curation.model.challenge.JoinChallengeRequest;
import com.web.curation.service.ChallengeService;
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
@RequestMapping("/challenge")
public class ChallengeController {
    @Autowired
    ChallengeService challengeService;

    @GetMapping
    @ApiOperation(value = "챌린지 전체 리스트 반환")
    public Object challengeList() {
        return new ResponseEntity<>(challengeService.challengeList(), HttpStatus.OK);
    }

    @GetMapping("/list/{page}")
    @ApiOperation(value = "챌린지 {페이지} 리스트")
    public Object list(@PathVariable("page") int page){
        return challengeService.challengePageList(page);
    }

    @GetMapping("/user/{userNo}")
    @ApiOperation(value = "나의 도전중인 챌린지 리스트 반환")
    public Object myChallengeList(@PathVariable("userNo") Long userNo) {
        return new ResponseEntity<>(challengeService.myChallengeList(userNo), HttpStatus.OK);
    }

//    @GetMapping("/user/{userNo}/{chglNo}")
//    @ApiOperation(value = "내가 좋아요하는 챌린지디테일 리스트 반환")
//    public Object myChlgDetailList(@RequestParam(required = true) final Long userNo, Long chlgNo) {
//        return new ResponseEntity<>(challengeService.myChlgDetailList(userNo, chlgNo), HttpStatus.OK);
//    }

    @GetMapping("/{chlgNo}")
    @ApiOperation(value = "챌린지 정보 반환")
    public Object challengeInfo(@PathVariable("chlgNo") Long chlgNo) {
        return new ResponseEntity<>(challengeService.challengeInfo(chlgNo), HttpStatus.OK);
    }

    @GetMapping("/detail/{chlgNo}")
    @ApiOperation(value = "챌린지 세부 정보 반환")
    public Object challengeDetailInfo(@PathVariable("chlgNo") Long chlgNo) {
        return new ResponseEntity<>(challengeService.challengeDetailInfo(chlgNo), HttpStatus.OK);
    }

    @PostMapping("/detail")
    @ApiOperation(value = "챌린지 도전하기 (사용자 정보 등록)")
    public Object joinChallenge(@Valid @RequestParam Long userNo,
                                @Valid @RequestParam Long chlgNo,
                                @Valid @RequestParam("image") MultipartFile file) {
        JoinChallengeRequest request = new JoinChallengeRequest(userNo, chlgNo, "");
        return new ResponseEntity<>(challengeService.joinChallenge(request, file), HttpStatus.OK);
    }

    @PutMapping("/up/{chlgNo}")
    @ApiOperation(value = "챌린지 좋아요 up")
    public Object challengeLikeUp(@PathVariable("chlgNo") Long chlgNo) {
        return new ResponseEntity<>(challengeService.challengeLikeUp(chlgNo), HttpStatus.OK);
    }

    @PutMapping("/down/{chlgNo}")
    @ApiOperation(value = "챌린지 좋아요 down")
    public Object challengeLikeDown(@PathVariable("chlgNo") Long chlgNo) {
        return new ResponseEntity<>(challengeService.challengeLikeDown(chlgNo), HttpStatus.OK);
    }

    @PutMapping("/{chlgNo}/up/{chlgDetailNo}")
    @ApiOperation(value = "챌린지 게시글 점수 up")
    public Object challengeDetailScoreUp(@PathVariable("chlgNo") Long chlgNo, Long chlgDetailNo) {
        return new ResponseEntity<>(challengeService.challengeDetailScoreUp(chlgNo, chlgDetailNo), HttpStatus.OK);
    }

    @PutMapping("/{chlgNo}/down/{chlgDetailNo}")
    @ApiOperation(value = "챌린지 게시글 점수 down")
    public Object challengeDetailScoreDown(@PathVariable("chlgNo") Long chlgNo, Long chlgDetailNo) {
        return new ResponseEntity<>(challengeService.challengeDetailScoreDown(chlgNo, chlgDetailNo), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "챌린지 등록")
    public Object saveChallenge(@Valid @RequestParam String title,
                                @Valid @RequestParam String content,
                                @Valid @RequestParam String startDate,
                                @Valid @RequestParam String endDate,
                                @Valid @RequestParam Long userNo,
                                @Valid @RequestParam("image") MultipartFile file) {

        ChallengeRequest request = new ChallengeRequest(title,content, "", LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE),
                LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE), userNo);
        return new ResponseEntity<>(challengeService.saveChallenge(request, file), HttpStatus.OK);
    }
}