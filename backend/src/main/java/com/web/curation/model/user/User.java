package com.web.curation.model.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
//실제 DB의 테이블고 매칭될 클래스임을 명시
@Data
// @Getter @Setter @EqualsAndHashCode @AllArgsConstructor 을 포함한 Lombok에서 제공하는 필드와 관련된 모든 코드를 생성한다.
@org.hibernate.annotations.DynamicUpdate
@NoArgsConstructor
//기본 생성자를 자동으로 추가함
// Entity 클래스를 프로젝트 코드상에서 기본생성자로 생성하는 것은 막되, JPA에서 Entity 클래스를 생성하는것은 허용하기 위해 추가한다.
@AllArgsConstructor
// 모든 필드 값을 파라미터로 받는 생성자를 추가한다.
@JsonInclude(JsonInclude.Include.NON_NULL)
// annotation 속성을 제외할 때 사용함
// NON_NULL 사용 시 name이 null인 경우에 제외된다.
@Getter
@Setter
public class User {
    @Id
    //@Column(insertable = false, updatable = false)
    // 해당 테이블의 PK 필드를 나타낸다.
    // 서버 에러나는 부분 500 uid가 not null 상태로 들어간 듯: 주석처리 하니까 정상
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // PK의 생성 규칙을 나타낸다.
    private Long userNo;

    //@JsonIgnore
    // 필드 레벨에서 무시할 속성을 표시할 때 사용
    private String userId;
    private String userPw;
    private String userEmail;
    private String userName;
    private String userImg;
    @Column(insertable = false, updatable = false)
    // insertable은 엔티티 저장시 이 필드도 같이 저장함. false로 선언하면 db에 저장하지 않음. 읽기 전용
    // updatable: 위와 동일. 하지만 수정일때 해당.
    private LocalDateTime createDate;

    @Builder
    public User( String userId, String userPw, String userEmail, String userName, String userImg) {
        this.userId = userId;
        this.userPw = userPw;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userImg = userImg;
    }

}