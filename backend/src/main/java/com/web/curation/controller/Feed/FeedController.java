package com.web.curation.controller.Feed;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.feed.Feed;
import com.web.curation.model.feed.FeedRequest;
import com.web.curation.service.FeedService;
import com.web.curation.service.S3FileUploadService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.util.Base64Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/feed")
public class FeedController {

    @Autowired
    FeedService feedservice;

    @Autowired
    S3FileUploadService S3;

    @GetMapping("/list/{page}")
    @ApiOperation(value = "피드리스트")
    public Object list(@PathVariable("page") int page)
    {
        return feedservice.list(page);
    }
//    @GetMapping(value = "/list/{img}", produces = MediaType.IMAGE_JPEG_VALUE)
//    @ApiOperation(value = "피드 이미지")
//    public ResponseEntity<byte[]> feedimage(@PathVariable("img") String img) throws IOException {
//        InputStream imageStream = new FileInputStream("/home/ubuntu/images/feed/wire.png");
//        byte[] imageByteArray = IOUtils.toByteArray(imageStream);
//        imageStream.close();
//        //InputStream in = getClass().getResourceAsStream("C://images/wire.png");
//        //System.out.println(img);
//        return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
//    }

    @GetMapping("/{userId}")
    @ApiOperation(value = "나의 피드 리스트 반환")
    public Object myFeedList(@RequestParam(required = true) final String userId) {
        return new ResponseEntity<>(feedservice.myFeedList(userId), HttpStatus.OK);
    }

    @PostMapping("/create")
    @ApiOperation(value = "피드생성", notes = "피드 정보 저장하고 이미지 저장작업")
    public Object create(
            @Valid @RequestParam("feedTitle") String title,
            @Valid @RequestParam("feedText") String text,
            @Valid @RequestParam("userId") String id,
            @Valid @RequestParam("img") MultipartFile file)
    {
        LocalDateTime cur_time = LocalDateTime.now();

        // 시간과 originalFilename으로 ㅍ매핑 시켜서 src 주소를 만들어 낸다.
        //Date date = new Date();
        StringBuilder sb = new StringBuilder();
        Feed feed = null;
        // file image가 없을 경우

        if(file.isEmpty()) {
            sb.append("none");
            return "fail";
        }
        else
        {
            sb.append(cur_time);
            sb.append(id);
            sb.append(file.getOriginalFilename());
        }

        feed = new Feed(title, text, id, cur_time,0, 0);
        feed.setFeedImg(("https://bodybuddy.s3.ap-northeast-2.amazonaws.com/feed/" + sb.toString()).replaceAll(" ", ""));
        feedservice.create(feed);

        return S3.upload(file, "feed/"+sb.toString());
    }

    @DeleteMapping("/deleteAll")
    @ApiOperation(value = "피드제거", notes = "계정 삭제시 관련 된 모든 feed 삭제")
    public void delete(@Valid @RequestParam("id") String id)
    {
        feedservice.delete(id);
    }
}
