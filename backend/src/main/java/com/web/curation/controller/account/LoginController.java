package com.web.curation.controller.account;

import com.web.curation.jwt.exception.TokenRefreshException;
import com.web.curation.model.feed.Feed;
import com.web.curation.model.user.*;
import com.web.curation.repo.user.UserRepo;
import com.web.curation.service.*;
import com.web.curation.jwt.JwtUtil;
import com.web.curation.model.user.*;
import com.web.curation.repo.user.UserRepo;
import com.web.curation.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@Slf4j
public class LoginController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService service;

    @Autowired
    UserService userService;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    S3FileUploadService S3;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/account/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    authRequest.getEmail(),
                    authRequest.getPassword())
            );

        } catch (Exception e) {
            throw new Exception(e);
        }

        final UserDetails userDetails = service.loadUserByUsername(authRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getUsername());

        return ResponseEntity.ok(new AuthenticationResponse(jwt, refreshToken.getToken()));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtil.extractUsername(user.getUserEmail());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }

    @PostMapping("/account/signup")
    @ApiOperation(value = "가입하기")
    public Object signup(@Valid @RequestBody SignupRequest request) {
        return new ResponseEntity<>(userService.insertUser(request), HttpStatus.OK);
    }
    //dupchk
    @GetMapping("/account/idchk")
    @ApiOperation(value = "id 중복 체크")
    public boolean idchk(@Valid @RequestParam("id") String id)
    {
        User user = userRepository.findUserByUserId(id);
        if(user == null)
            return true;
        else
            return false;
    }

    @ApiOperation(value = "정보 조회")
    @GetMapping("/account/userinfo")
    public Object userinfo(@RequestParam("jwt") String jwt)
    {

        String email = jwtUtil.validateToken(jwt);
        System.out.println(email);
        User user = null;

        if(email != "token expired") {
            user = userRepository.findByUserEmail(email);
            return user;
        }
        else
            return "expired";
        //user.setUserId(id);
    }
    @ApiOperation(value = "유저 기본 이미지 수정")
    @GetMapping("/account/standimg")
    public Object standimg(@RequestParam String id) {
        System.out.println(id);
        Optional<User> update = userRepository.findByUserId(id);
        update.ifPresent(u->{
            u.setUserImg(("https://bodybuddy.s3.ap-northeast-2.amazonaws.com/user/stand.jpg"));
            userRepository.save(u);
        });
        return "suc";
    }
    @ApiOperation(value = "유저 이미지 수정")
    @PutMapping("/account/userimg")
    public Object userimg(@RequestParam MultipartFile image, @RequestParam String id)
    {
        Optional<User> update = userRepository.findByUserId(id);

        LocalDateTime cur_time = LocalDateTime.now();

        // 시간과 originalFilename으로 ㅍ매핑 시켜서 src 주소를 만들어 낸다.
        //Date date = new Date();
        StringBuilder sb = new StringBuilder();
        // file image가 없을 경우

        if(image.isEmpty()) {
            sb.append("none");
            return "fail";
        }
        else
        {
            sb.append(cur_time);
            sb.append(id);
            sb.append(image.getOriginalFilename());
        }
        S3.upload(image, "user/"+sb.toString());

        update.ifPresent(u->{
            u.setUserImg(("https://bodybuddy.s3.ap-northeast-2.amazonaws.com/user/" + sb.toString()).replaceAll(" ", ""));
            userRepository.save(u);
        });
        return "suc";
    }

    @ApiOperation(value = "정보 수정 (현재는 name만 바꿀 수 있음)")
    @PutMapping("/account/modify")
    public Object modify(@RequestBody User user)
    {
        Optional<User> update = userRepository.findByUserId(user.getUserId());
        
        update.ifPresent(u->{
            // u.setUserPw(passwordEncoder.encode(user.getUserPw()));
            u.setUserPw(user.getUserPw());
            userRepository.save(u);
        });
        return "suc";
    }

    @ApiOperation(value = "회원 탈퇴")
    @DeleteMapping("/account/delete")
    public Integer delete(@RequestParam String id)
    {
        return userRepository.removeUserByUserId(id);
    }
}
