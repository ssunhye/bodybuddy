package com.web.curation.model.exercise;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Powerlifting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long plNo;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    @Column(name = "user_no", insertable = false, updatable = false)
    private Long userNo;

    private Double squat;
    private Double benchpress;
    private Double deadlift;
    private LocalDate date;

    @Column(insertable = false, updatable = false)
    private LocalDateTime createDate;

    @Builder
    public Powerlifting(Double squat, Double benchpress, Double deadlift, LocalDate date) {
        this.squat = squat;
        this.benchpress = benchpress;
        this.deadlift = deadlift;
        this.date = date;
    }
}