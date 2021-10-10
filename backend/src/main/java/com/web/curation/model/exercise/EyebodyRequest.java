package com.web.curation.model.exercise;

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
public class EyebodyRequest {
    @ApiModelProperty(required = true)
    String img;
    @ApiModelProperty(required = true)
    @NotNull
    LocalDate date;
    @ApiModelProperty(required = true)
    @NotNull
    Long userNo;
}
