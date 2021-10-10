package com.web.curation.model.exercise;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.web.curation.model.user.User;
import lombok.*;
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
public class Eyebody {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eyebodyNo;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    @Column(name = "user_no", insertable = false, updatable = false)
    private Long userNo;

    @Column
    private String img;

    @Column
    private LocalDate date;

    @Builder
    public Eyebody(String img, LocalDate date) {
        this.img = img;
        this.date = date;
    }
}
