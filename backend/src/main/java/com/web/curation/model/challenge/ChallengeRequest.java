package com.web.curation.model.challenge;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Valid
@ToString
@Getter
@Setter
@AllArgsConstructor
public class ChallengeRequest {
    @ApiModelProperty(required = true)
    @NotNull
    String title;
    @ApiModelProperty(required = true)
    @NotNull
    String content;
    @ApiModelProperty(required = true)
    //@NotNull
    String img;
    @ApiModelProperty(required = true)
    @NotNull
    LocalDate startDate;
    @ApiModelProperty(required = true)
    @NotNull
    LocalDate endDate;
    @ApiModelProperty(required = true)
    @NotNull
    Long userNo;
}
