package com.cgi.prototype.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author aralco
 */
@Component
@ConfigurationProperties(prefix = "aws.s3")
public class AwsS3Properties {
    private String bucket;

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }
}
