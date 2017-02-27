package com.cgi.prototype.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.cgi.prototype.config.AwsS3Properties;
import com.cgi.prototype.exception.FileStorageNotFoundException;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class AwsS3FileStorageService implements FileStorageService {

    @Autowired
    private AmazonS3 amazonS3;
    private final AwsS3Properties awsS3Properties;

    @Autowired
    public AwsS3FileStorageService(AwsS3Properties awsS3Properties) {
        this.awsS3Properties = awsS3Properties;
    }

    @Override
    public void saveFile(MultipartFile file, String key) {
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.getSize());
            objectMetadata.setContentType(file.getContentType());;
            objectMetadata.setContentMD5(new String(Base64.encodeBase64(DigestUtils.md5Digest(file.getBytes()))));
            amazonS3.putObject(
                    new PutObjectRequest(
                            awsS3Properties.getBucket(),
                            (key ==null)?file.getOriginalFilename(): key,
                            file.getInputStream(),
                            objectMetadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (AmazonClientException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<S3ObjectSummary> listFiles() {
        ObjectListing objectListing = amazonS3.listObjects(new ListObjectsRequest().withBucketName(awsS3Properties.getBucket()));
        return objectListing.getObjectSummaries();
    }

    @Override
    public Resource findFile(String key) {
        try {
        GetObjectRequest getObjectRequest = new GetObjectRequest(awsS3Properties.getBucket(), key);
        S3Object s3Object = amazonS3.getObject(getObjectRequest);
        S3ObjectInputStream objectInputStream = s3Object.getObjectContent();

            Resource resource = new ByteArrayResource(IOUtils.toByteArray(objectInputStream));

            if(resource.exists() && resource.isReadable())
                return resource;
            else
                throw new FileStorageNotFoundException("File is not healthy");

        } catch (AmazonS3Exception | IOException e) {
            throw new FileStorageNotFoundException("File with key: "+key+" does not exist on S3 storage.");
        }
    }

}
