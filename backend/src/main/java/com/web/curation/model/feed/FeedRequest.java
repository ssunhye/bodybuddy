package com.web.curation.model.feed;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Valid
@ToString
@Getter
@Setter
public class FeedRequest {
    @ApiModelProperty(required = true)
    @NotNull
    private MultipartFile img;

    @ApiModelProperty(required = true)
    @NotNull
    private String feedTitle;

    @ApiModelProperty(required = true)
    @NotNull
    private String feedText;

    @ApiModelProperty(required = true)
    @NotNull
    private String userId;
}
