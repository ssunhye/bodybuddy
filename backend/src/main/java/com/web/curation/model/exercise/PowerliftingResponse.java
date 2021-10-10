package com.web.curation.model.exercise;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@Valid
@ToString
@Getter
@Setter
public class PowerliftingResponse {
    @ApiModelProperty(required = true)
    @NotNull
    Double squat;
    @ApiModelProperty(required = true)
    @NotNull
    Double benchpress;
    @ApiModelProperty(required = true)
    @NotNull
    Double deadlift;
}
