package com.web.curation.model.challenge;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Valid
@ToString
@Getter
@Setter
@AllArgsConstructor
public class VoteUpDownRequest {
    @ApiModelProperty(required = true)
    @NotNull
    Long userNo;
    @ApiModelProperty(required = true)
    @NotNull
    Long chlgDetailNo;
}
