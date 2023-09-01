package com.myblog.blogapp.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PostDto {
    private long id;
    @NotNull
    @Size(min=2,message = "post title should have at least two characters")
    private String title;

    @NotNull
    @Size(min=10,message = "post title should have at least ten or more characters")
    private String description;

    @NotNull
    @NotEmpty
    private String content;
}
