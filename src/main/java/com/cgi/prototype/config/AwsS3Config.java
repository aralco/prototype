package com.cgi.prototype.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author aralco
 */
@Configuration
public class AwsS3Config {
    @Autowired
    private AWSCredentials awsCredentials;

    @Bean
    public AmazonS3 awsS3Client()  {
        return new AmazonS3Client(awsCredentials);
    }

}
