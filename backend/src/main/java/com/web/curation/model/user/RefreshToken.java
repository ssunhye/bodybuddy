package com.web.curation.model.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
public class RefreshToken {
    @Id
    private Long userNo;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_no")
    private User user;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Instant expirationDate;
}
