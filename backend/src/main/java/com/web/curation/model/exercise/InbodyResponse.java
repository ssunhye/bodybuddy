package com.web.curation.model.exercise;

import io.swagger.annotations.ApiModelProperty;
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
public class InbodyResponse {
    @ApiModelProperty(required = true)
    @NotNull
    Long inbodyNo;
    @ApiModelProperty(required = true)
    @NotNull
    Double weight;
    @ApiModelProperty(required = true)
    @NotNull
    Double fat;
    @ApiModelProperty(required = true)
    @NotNull
    Double muscle;
    @ApiModelProperty(required = true)
    @NotNull
    Double bmi;
    @ApiModelProperty(required = true)
    @NotNull
    Integer score;
    @ApiModelProperty(required = true)
    @NotNull
    LocalDate date;
}
