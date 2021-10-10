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
public class TimeRecordResponse {
    @ApiModelProperty(required = true)
    @NotNull
    String start;
    @ApiModelProperty(required = true)
    @NotNull
    String end;
}
