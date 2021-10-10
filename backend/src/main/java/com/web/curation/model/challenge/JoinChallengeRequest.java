package com.web.curation.model.challenge;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Valid
@ToString
@Getter
@Setter
@AllArgsConstructor
public class JoinChallengeRequest {
    @ApiModelProperty(required = true)
    @NotNull
    Long userNo;
    @ApiModelProperty(required = true)
    @NotNull
    Long chlgNo;
    @ApiModelProperty(required = true)
    @NotNull
    String img;
}
