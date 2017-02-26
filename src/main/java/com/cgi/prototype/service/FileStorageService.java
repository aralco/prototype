package com.cgi.prototype.service;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author aralco
 */
public interface FileStorageService {
    void saveFile(MultipartFile file, String key);
    List<S3ObjectSummary> listFiles();
    Resource findFile(String key);
}
