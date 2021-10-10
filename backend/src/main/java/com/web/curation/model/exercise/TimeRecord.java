package com.web.curation.model.exercise;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.web.curation.model.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class TimeRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long timeRecordNo;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    @Column(name = "user_no", insertable = false, updatable = false)
    private Long userNo;

    private String start;
    private String end;

    @Column(insertable = false, updatable = false)
    private LocalDateTime createDate;

    @Builder
    public TimeRecord(String start, String end) {
        this.start = start;
        this.end = end;
    }
}
