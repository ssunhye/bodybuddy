package com.web.curation.model.exercise;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.web.curation.model.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class Inbody {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inbodyNo;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    @Column(name = "user_no", insertable = false, updatable = false)
    private Long userNo;

    private Double weight;
    private Double fat;
    private Double muscle;
    private Double bmi;
    private Integer score;
    private LocalDate date;

    @Column(insertable = false, updatable = false)
    private LocalDateTime createDate;

    @Builder
    public Inbody(Double weight, Double fat, Double muscle, Double bmi, Integer score, LocalDate date) {
        this.weight = weight;
        this.fat = fat;
        this.muscle = muscle;
        this.bmi = bmi;
        this.score = score;
        this.date = date;
    }
}
