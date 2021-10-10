package com.web.curation.model.user;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.Scope;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigInteger;


@Valid
@ToString
@Getter
@Setter
public class SignupRequest {
    @ApiModelProperty(required = true)
    @NotNull
    String userName;
    @ApiModelProperty(required = true)
    @NotNull
    String userId;
    @ApiModelProperty(required = true)
    @NotNull
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d$@$!%*#?&]{8,}$")
    String userPw;
    @ApiModelProperty(required = true)
    @NotNull
    String userEmail;
    @ApiModelProperty(required = true)
    @NotNull
    String userImg;
}
