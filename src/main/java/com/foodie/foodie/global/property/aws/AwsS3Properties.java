package com.foodie.foodie.global.property.aws;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties("aws.s3")
public class AwsS3Properties {

    private final String url;

    private final String bucket;

    private final String foodieFolder;

}
