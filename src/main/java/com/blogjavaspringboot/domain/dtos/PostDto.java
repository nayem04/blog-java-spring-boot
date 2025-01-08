package com.blogjavaspringboot.domain.dtos;

import com.blogjavaspringboot.common.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PostDto extends BaseDto {
    @NotBlank(message = "Title is required.")
    @JsonProperty(value = "title")
    private String title;

    @NotBlank(message = "Content is Required.")
    @Size(min = 50, max = 10000, message = "Content length must be between 50 to 10000 characters.")
    @JsonProperty(value = "content")
    private String content;

    @NotNull(message = "User Id is required.")
    @JsonProperty(value = "user_id")
    private Long userId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
