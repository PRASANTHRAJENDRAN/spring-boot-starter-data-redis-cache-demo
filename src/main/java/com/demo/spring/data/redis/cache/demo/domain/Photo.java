package com.demo.spring.data.redis.cache.demo.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Photo implements Serializable {
    private String id;
    private String title;
    private String url;
    private String thumbnailUrl;
}
