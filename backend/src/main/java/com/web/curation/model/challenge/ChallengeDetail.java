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
public class ChallengeDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chlgDetailNo;

    @ManyToOne
    @JoinColumn(name = "chlg_no")
    private Challenge challenge;

    @Column(name = "chlg_no", insertable = false, updatable = false)
    private Long chlgNo;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    @Column(name = "user_no", insertable = false, updatable = false)
    private Long userNo;

    @Column
    private String img;

    @Column
    @ColumnDefault("0")
    private Long score;

    @Builder
    public ChallengeDetail(String img) {
        this.img = img;
    }
}
