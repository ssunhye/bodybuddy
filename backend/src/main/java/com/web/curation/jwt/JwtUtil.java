package com.web.curation.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {
    private String secret = "bodya105@";

    //Payload 부분에는 토큰에 담을 정보가 들어있다.
    //여기에 담는 정보의 한 ‘조각’ 을 클레임(claim) 이라고 부르고,
    //이는 key / value 의 한 쌍으로 이뤄져있다.
    //토큰에는 여러개의 클레임 들을 넣을 수 있다.

    //JWT 토큰에서 username을 획득한다.
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    //JWT 토큰에서 만료날짜 획득
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    //jwt토큰에서 정보를 획득한다
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    //프로퍼티의 시크릿키를 이용하여 만들어진 JWS를
    //시크릿키를 이용하여 파싱하고,
    //페이로드의 Claims를 리턴한다.
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //JWT 토큰이 만료되었는지 확인
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    //사용자를 위한 JWT 토큰 생성
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    //JWT 토큰 생성 
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
            .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    //JWT 토큰이 유효한지 체크
    //-토큰의 subject와 유저의 useremail이 동일한지
    //-토큰이 만료날짜가 유효한지
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String validateToken(String token) {
            return extractUsername(token);
    }

}