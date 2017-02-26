package com.cgi.prototype.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author aralco
 */
@Configuration
public class AwsConfig {

    private final AwsProperties awsProperties;

    @Autowired
    public AwsConfig(AwsProperties awsProperties) {
        this.awsProperties = awsProperties;
    }


    @Bean
    public AWSCredentials awsCredentials() {
        return new BasicAWSCredentials(awsProperties.getAccessKeyId(), awsProperties.getSecretKey());
    }

}
