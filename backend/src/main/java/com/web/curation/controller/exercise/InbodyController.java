package com.web.curation.controller.exercise;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.exercise.Inbody;
import com.web.curation.model.exercise.InbodyRequest;
import com.web.curation.service.InbodyService;
import com.web.curation.service.S3FileUploadService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.UUID;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@RestController
@RequestMapping("/inbody")
public class InbodyController {
    @Autowired
    InbodyService inbodyService;

    @Autowired
    S3FileUploadService S3;

    @GetMapping("/{userNo}")
    @ApiOperation(value = "사용자 인바디 정보 반환")
    public Object inbodyInfo(@RequestParam(required = true) final Long userNo) {
        return new ResponseEntity<>(inbodyService.inbodyInfo(userNo), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "사용자 인바디 정보 등록")
    public Object insertInbody(@RequestParam(required = true) final Long userNo, @Valid @RequestBody InbodyRequest request) {
        return new ResponseEntity<>(inbodyService.saveInbody(userNo, request), HttpStatus.OK);
    }

    @DeleteMapping("/{inbodyNo}")
    @ApiOperation(value = "사용자 인바디 정보 삭제")
    public Object deleteInbody(@RequestParam(required = true) final Long inbodyNo) {
        return new ResponseEntity<>(inbodyService.deleteInbody(inbodyNo), HttpStatus.OK);
    }

    @PutMapping("/{inbodyNo}")
    @ApiOperation(value = "사용자 인바디 정보 수정")
    public Object updateInbody(@RequestParam(required = true) final Long inbodyNo, @Valid @RequestBody InbodyRequest request) {
        return new ResponseEntity<>(inbodyService.updateInbody(inbodyNo, request), HttpStatus.OK);
    }

//    @PostMapping("/imgupload")
//    @ApiOperation(value = "이미지 업로드")
//    public Object insertImg(@Valid @RequestParam Long userNo, @Valid @RequestParam Long inbodyNo, @Valid @RequestParam("img") MultipartFile file)
//    {
//        LocalDateTime cur_time = LocalDateTime.now();
//
//        // 시간과 originalFilename으로 ㅍ매핑 시켜서 src 주소를 만들어 낸다.
//        //Date date = new Date();
//        StringBuilder sb = new StringBuilder();
//
//        // file image가 없을 경우
//        if(file.isEmpty()) {
//            sb.append("none");
//            return "file is empty";
//        }
//        else
//        {
//            sb.append(cur_time);
//            sb.append(file.getOriginalFilename());
//        }
//        Inbody inbody = inbodyService.inbodyInfo(userNo,inbodyNo);
//        if(inbody != null)
//        {
//            inbody.setImg("https://bodybuddy.s3.ap-northeast-2.amazonaws.com/inbody/" + sb.toString());
//            S3.upload(file, "inbody/" + sb.toString());
//            inbodyService.updateInbody(inbody);
//            return true;
//        }
//        else
//            return false;
//    }
    @PostMapping("/ocr")
    @ApiOperation(value = "ocr")
    public Object ocr(@Valid @RequestParam("img") MultipartFile img) {
        String apiURL = "https://ad62f038a25f4cfa8ff29508cf395b41.apigw.ntruss.com/custom/v1/10577/e424b13a41c17f19c6a2e29aafcac9226570409cd93b0fe859d6f6bf387938f0/general";
        String secretKey = "bEZBRWlod21xYnlJdnZWdmFNaGpyYlJYZGpIeGRaUEY=";
        // 전체적인 흐름은 파일을 가져와 로컬에 저장하고 이를 토대로 naver ocr로 보내 문자를 추출한다. 후에 로컬에 저장된 이미지 파일을 다시 지운다.
        // 단순 naver ocr을 이용하는 것 처럼 보이지만 저장 경로를 제대로 운용하지 못하면 까다로운 문제가 생긴다.
        File file = null;
        try {
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setUseCaches(false);
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setReadTimeout(30000);
            con.setRequestMethod("POST");
            String boundary = "----" + UUID.randomUUID().toString().replaceAll("-", "");
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            con.setRequestProperty("X-OCR-SECRET", secretKey);

            JSONObject json = new JSONObject();
            json.put("version", "V2");
            json.put("requestId", UUID.randomUUID().toString());
            json.put("timestamp", System.currentTimeMillis());
            JSONObject image = new JSONObject();
            image.put("format", "jpg");
            image.put("name", "demo");
            JSONArray images = new JSONArray();
            images.put(image);
            json.put("images", images);
            String postParams = json.toString();

            con.connect();
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            long start = System.currentTimeMillis();
            // 여긴 항상 헷갈리니까 정리.
            // window에서 그냥 돌릴 경우 "c://~"로 지정할 수 있다.
            // 하지만 우리 프로젝트 경우 docker 위에서 돌리기 떄문에
            // sudo docker exec -it backend sh 로 접속 후
            // tmp 경로를 따라가다보면 localhost가 나온다 사실상 거기부터 시작이다.
            // 하지만 docker를 재구성할 경우 tomcat 폴더가 달라진다 따라서 거슬러 올라와 tmp 폴더에 이미지를 저장한다.
            file = new File("../../../../../" + img.getOriginalFilename());
            //if(file.exists())
                //.delete();
            img.transferTo(file);
            // 저장된 이미지를 불러오기 위해선 경로가 바뀌어야 한다. 원래 의도대로 /tmp/파일명 경로를 따라가면 파일을 가져올 것이다.
            file = new File("/tmp/" + img.getOriginalFilename());

            writeMultiPart(wr, postParams, file, boundary);
            wr.close();

            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                if(file.exists())
                    file.delete();
                System.out.println(responseCode);
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();

            System.out.println(response);
            file.delete();
            return response;
        } catch (Exception e) {
            System.out.println(e);
            return "fail";
        }
    }

    private static void writeMultiPart (OutputStream out, String jsonMessage, File file, String boundary) throws
            IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("--").append(boundary).append("\r\n");
        sb.append("Content-Disposition:form-data; name=\"message\"\r\n\r\n");
        sb.append(jsonMessage);
        sb.append("\r\n");

        out.write(sb.toString().getBytes("UTF-8"));
        out.flush();

        if (file != null && file.isFile()) {
            out.write(("--" + boundary + "\r\n").getBytes("UTF-8"));
            StringBuilder fileString = new StringBuilder();
            fileString
                    .append("Content-Disposition:form-data; name=\"file\"; filename=");
            fileString.append("\"" + file.getName() + "\"\r\n");
            fileString.append("Content-Type: application/octet-stream\r\n\r\n");
            out.write(fileString.toString().getBytes("UTF-8"));
            out.flush();

            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] buffer = new byte[8192];
                int count;
                while ((count = fis.read(buffer)) != -1) {
                    out.write(buffer, 0, count);
                }
                out.write("\r\n".getBytes());
            }

            out.write(("--" + boundary + "--\r\n").getBytes("UTF-8"));
        }
        out.flush();
    }

}
