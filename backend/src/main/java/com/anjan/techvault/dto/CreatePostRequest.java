package com.anjan.techvault.dto;

import lombok.Data;

@Data
public class CreatePostRequest {
    private String title;
    private String content;
    private Boolean featured = false;
}