package com.cs.cn.controller;

import com.cs.cn.constans.ApplicationConstants;
import com.cs.cn.constans.ServerConstants;
import com.cs.cn.service.FileService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = ServerConstants.CLIENT_FRONTEND)
@RequestMapping(ApplicationConstants.APPLICATION_TICKET_TRACE + ApplicationConstants.RESOURCE_FILES)
public class FileController {

    private final FileService fileService;
    
    private final HttpServletRequest httpServletRequest;

    @PostMapping("/upload")
    public Map<String, String> uploadPhoto(
            @RequestParam("file") MultipartFile file) throws IOException {
        String path = fileService.uploadPhotoAgent(file);
        String host = httpServletRequest.getRequestURL().toString().replace(httpServletRequest.getRequestURI(), "");
        String url = ServletUriComponentsBuilder
                .fromHttpUrl(host)
                .path(ApplicationConstants.APPLICATION_TICKET_TRACE + ApplicationConstants.RESOURCE_FILES + "/userPhoto/")
                .path(path)
                .toUriString();
        return Map.of("url", url);
    }
    
    @GetMapping("/userPhoto/{fileName:.+}")
    public ResponseEntity<Resource> getUserPhoto(@PathVariable String fileName) throws IOException {
        Resource file = fileService.loadAsResource(fileName);
        String contentType = Files.probeContentType(file.getFile().toPath());
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(file);
    }
    
}
