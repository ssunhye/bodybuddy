package com.web.curation.model.exercise;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Valid
@ToString
@Getter
@Setter
public class TimeRecordRequest {
    @ApiModelProperty(required = true)
    @NotNull
    String start;
    @ApiModelProperty(required = true)
    @NotNull
    String end;
}
