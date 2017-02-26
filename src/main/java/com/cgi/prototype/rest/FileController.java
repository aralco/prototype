package com.cgi.prototype.rest;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.cgi.prototype.exception.FileStorageNotFoundException;
import com.cgi.prototype.service.FileStorageService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author aralco
 */
@Api
@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(
            @RequestParam(name = "file") MultipartFile multipartFile,
            @RequestParam(name = "key", required = false) String key
    ) {
        fileStorageService.saveFile(multipartFile, key);
        return ResponseEntity.ok("File uploaded");
    }

    @GetMapping
    public ResponseEntity<List<S3ObjectSummary>> listFiles()   {

        return new ResponseEntity<>(fileStorageService.listFiles(), HttpStatus.OK);
    }

    @GetMapping("/{key:.+}")
    public ResponseEntity<Resource> downloadDocument(
            @PathVariable(name = "key") String key)  {
        Resource resource = fileStorageService.findFile(key);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+key+"\"")
                .body(resource);
    }

    @ExceptionHandler(FileStorageNotFoundException.class)
    public ResponseEntity handleFileStorageNotFound(FileStorageNotFoundException e) {
        return ResponseEntity.notFound().build();
    }

}
