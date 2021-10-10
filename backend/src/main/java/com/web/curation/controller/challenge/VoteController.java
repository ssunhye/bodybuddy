package com.web.curation.controller.challenge;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.challenge.VoteRequest;
import com.web.curation.model.challenge.VoteUpDownRequest;
import com.web.curation.service.VoteService;
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
@RequestMapping("/vote")
public class VoteController {
    @Autowired
    VoteService voteService;

    @GetMapping
    @ApiOperation(value = "내가 투표한 chanllenge detail 반환")
    public Object voteInfo(@RequestParam(required = true) final Long userNo, @RequestParam(required = true) final Long chlgNo) {
        return new ResponseEntity<>(voteService.voteInfo(userNo, chlgNo), HttpStatus.OK);
    }

    @PostMapping("/up")
    @ApiOperation(value = "투표 up")
    public Object voteUp(@Valid @RequestBody VoteUpDownRequest request) {
        return new ResponseEntity<>(voteService.voteUp(request), HttpStatus.OK);
    }

    @DeleteMapping("/down")
    @ApiOperation(value = "투표 down")
    public Object challengeLikeDown(@RequestParam(required = true) final Long userNo, @RequestParam(required = true) final Long chlgDetailNo) {
        return new ResponseEntity<>(voteService.voteDown(userNo, chlgDetailNo), HttpStatus.OK);
    }
}