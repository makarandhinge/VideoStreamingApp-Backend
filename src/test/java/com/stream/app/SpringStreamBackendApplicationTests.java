package com.stream.app;

import com.stream.app.service.VideoService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringStreamBackendApplicationTests {
    @Autowired
    VideoService videoService;

    @Test
    void contextLoads() {

        videoService.processVideo("6d68285a-d696-4a72-b4b9-aff6400e86eb",null);

    }

}
