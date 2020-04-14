package com.demo.spring.data.redis.cache.demo.service;

import com.demo.spring.data.redis.cache.demo.domain.Photo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class PhotoService {

    @CachePut(cacheNames = "photo", key = "#photo.id")
    public Photo createPhoto(Photo photo) {
        return photo;
    }

    @Cacheable(cacheNames = "photo", key = "#id")
    public Photo findById(String id) {
        return null;
    }

    @CachePut(cacheNames = "photo", key = "#id")
    public Photo updatePhoto(String id, Photo photo) {
        return photo;
    }

    @CacheEvict(cacheNames = "photo", key = "#id")
    public void deletePhoto(String id) {
    }

}
