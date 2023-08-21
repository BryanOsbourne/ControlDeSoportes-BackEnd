package com.cs.cn.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface FileService {
    public void init() throws IOException;
    public String uploadPhotoAgent(MultipartFile file) throws IOException;
    Resource loadAsResource(String filename);
}
