package com.web.curation.model.feed;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Document
@Getter
@Setter
@NoArgsConstructor
public class Feed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long feedNo;

    private String feedImg;
    private String feedTitle;
    private String feedText;
    private String userId;

    private int feedLike;
    private int feedCom;

    @Column(insertable = false, updatable = false)
    // insertable은 엔티티 저장시 이 필드도 같이 저장함. false로 선언하면 db에 저장하지 않음. 읽기 전용
    // updatable: 위와 동일. 하지만 수정일때 해당.
    private LocalDateTime feedDate;

    public Feed(String feedTitle, String feedText, String userId, LocalDateTime feedDate,int feedCom, int feedLike)
    {
        this.feedTitle = feedTitle;
        this.feedText = feedText;
        this.userId = userId;
        this.feedCom = feedCom;
        this.feedLike = feedLike;
        this.feedDate = feedDate;
    }

}
