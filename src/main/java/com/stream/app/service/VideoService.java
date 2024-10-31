package com.stream.app.service;

import com.stream.app.entity.Video;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService
    {

        //save video
        Video save(Video video, MultipartFile file);

        //get video by title
        Video getByTitle(String title);

        //get video by id
        Video get(String videoId);

        //get all video
        List<Video> getAll();

    }
