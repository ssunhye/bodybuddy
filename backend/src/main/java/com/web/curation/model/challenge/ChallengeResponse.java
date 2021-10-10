package com.web.curation.model.challenge;

import com.web.curation.model.user.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Valid
@ToString
@Getter
@Setter
public class ChallengeResponse {
    @ApiModelProperty(required = true)
    @NotNull
    Long chlgNo;

    @ApiModelProperty(required = true)
    @NotNull
    Long userNm;

    @ApiModelProperty(required = true)
    @NotNull
    Long totalUserCnt;

    @ApiModelProperty(required = true)
    @NotNull
    String title;

    @ApiModelProperty(required = true)
    @NotNull
    String desc;

    @ApiModelProperty(required = true)
    @NotNull
    Long likeCnt;

    @ApiModelProperty(required = true)
    @NotNull
    Long cnt;

    @ApiModelProperty(required = true)
    @NotNull
    LocalDateTime startDate;

    @ApiModelProperty(required = true)
    @NotNull
    LocalDateTime endDate;
}
