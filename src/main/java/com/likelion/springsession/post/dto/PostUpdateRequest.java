package com.likelion.springsession.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class PostUpdateRequest {

    @NotBlank
    @Size(max = 100)
    private String title;

    @NotBlank
    private String content;
}