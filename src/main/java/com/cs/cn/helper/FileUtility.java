package com.cs.cn.helper;

import com.cs.cn.constans.ApplicationConstants;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUtility {

    @Value("${media.location}")
    private String folderUserPhotos;

    private static Path rootLocation;

    private final HttpServletRequest httpServletRequest;

    public FileUtility(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @PostConstruct
    public void init() throws IOException {
        rootLocation = Paths.get(folderUserPhotos);
        Files.createDirectories(rootLocation);
    }

    public String uploadPhotoAgent(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file");
            }
            String filename = file.getOriginalFilename();
            Path destinationFile = rootLocation.resolve(Paths.get(filename))
                    .normalize().toAbsolutePath();
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
            String url = getUrlBuild(
                    ApplicationConstants.APPLICATION_TICKET_TRACE +
                            ApplicationConstants.RESOURCE_AGENT + "/userPhoto/",
                    filename
            );
            return url;
        } catch (IOException E) {
            throw new RuntimeException("Failed to store empty file");
        }
    }

    private String getUrlBuild(String endPoint, String fileName) {
        String host = httpServletRequest.getRequestURL().toString().replace(httpServletRequest.getRequestURI(), "");
        return ServletUriComponentsBuilder
                .fromHttpUrl(host)
                .path(endPoint)
                .path(fileName)
                .toUriString();
    }

    public Resource getAgentPhoto(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource((file.toUri()));
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read rile: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Could not read rile: " + filename);
        }
    }

}
