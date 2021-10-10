package com.web.curation.model.challenge;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.web.curation.model.user.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@DynamicUpdate
@DynamicInsert
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chlgNo;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    @Column(name = "user_no", insertable = false, updatable = false)
    private Long userNo;

    @Column
    private String title;
    @Column
    private String content;
    @Column
    private String img;

    @Column
    @ColumnDefault("0")
    private Long cnt;

    @Column
    @ColumnDefault("0")
    private Long likeCnt;

    @Column
    private LocalDate startDate;
    @Column
    private LocalDate endDate;

    @Builder
    public Challenge(String title, String content, String img, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.content = content;
        this.img = img;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
