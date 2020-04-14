package com.demo.spring.data.redis.cache.demo.service;

import com.demo.spring.data.redis.cache.demo.SpringBootStarterDataRedisDemoApplication;
import com.demo.spring.data.redis.cache.demo.domain.Photo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * This integration Test requires following external dependencies to run:
 * 1.Redis
 */
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringBootStarterDataRedisDemoApplication.class)
class PhotoServiceITest {

    @Autowired
    private PhotoService photoService = null;

    @Test
    void testCache() {
        Photo photoDto = setup();
        Photo photo = photoService.createPhoto(photoDto);
        Photo photoDtoGet = photoService.findById(photo.getId());
        assertNotNull(photoDtoGet);
        assertEquals(photoDto.getId(), photoDtoGet.getId());
        String title = photoDto.getTitle();
        photoDto.setTitle(UUID.randomUUID().toString());
        photoService.updatePhoto(photoDto.getId(), photoDto);
        photoDtoGet = photoService.findById(photo.getId());
        assertNotEquals(title, photoDtoGet.getTitle());
        photoService.deletePhoto(photo.getId());
        photoDtoGet = photoService.findById(photo.getId());
        assertNull(photoDtoGet);
    }


    private Photo setup() {
        Photo photoDto = new Photo();
        photoDto.setId(UUID.randomUUID().toString());
        photoDto.setThumbnailUrl(UUID.randomUUID().toString());
        photoDto.setTitle(UUID.randomUUID().toString());
        photoDto.setUrl(UUID.randomUUID().toString());
        return photoDto;
    }

}
