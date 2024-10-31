package com.stream.app.serviceImplementation;

import ch.qos.logback.core.util.StringUtil;
import com.stream.app.entity.Video;
import com.stream.app.repositories.VideoRepository;
import com.stream.app.service.VideoService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService
    {
        @Autowired
        private VideoRepository videoRepository;

        @Value("${files.video}")
        String DIR;

        @PostConstruct
        public void init()
            {
                File file = new File(DIR);
                if (!file.exists())
                    {
                        file.mkdirs();
                        System.out.println("Folder is created");
                    } else
                    {
                        System.out.println("Folder already exists");
                    }
            }

        @Override
        public Video save(Video video, MultipartFile file)
            {
                try
                    {
                        //original file name
                        String filename = file.getOriginalFilename();
                        String contentType = file.getContentType();
                        InputStream inputStream = file.getInputStream();
                        // file path
                        String cleanFileName = StringUtils.cleanPath(filename);
                        //folder path create
                        String cleanFolder = StringUtils.cleanPath(DIR);
                        //folder path  with filename
                        Path path = Paths.get(cleanFolder, cleanFileName);
                        System.out.println("This this path of the file" + path);

                        //copy file to the folder
                        Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
                        //video meta data
                        video.setContentType(contentType);
                        video.setFilePath(path.toString());
                        //metadata save
                        return videoRepository.save(video);


                    } catch (Exception e)
                    {
                        e.printStackTrace();
                        return null;
                    }
            }

        @Override
        public Video getByTitle(String title)
            {
                return null;
            }

        @Override
        public Video get(String videoId)
            {
                Video video = videoRepository.findById(videoId)
                                             .orElseThrow(() -> new RuntimeException("Video not found"));
                return video;
            }

        @Override
        public List<Video> getAll()
            {
                return videoRepository.findAll();
            }
    }
