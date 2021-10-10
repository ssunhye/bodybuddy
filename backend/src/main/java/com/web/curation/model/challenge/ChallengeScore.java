package com.web.curation.model.challenge;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.web.curation.model.user.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@DynamicUpdate
public class ChallengeScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chlgScoreNo;

    @ManyToOne
    @JoinColumn(name = "chlg_detail_no")
    private ChallengeDetail challengeDetail;

    @Column(name = "chlg_detail_no", insertable = false, updatable = false)
    private Long chlgDetailNo;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    @Column(name = "user_no", insertable = false, updatable = false)
    private Long userNo;
}
