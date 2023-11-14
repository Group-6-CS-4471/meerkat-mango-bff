package meerkat.mango.api.gateway.message;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.EC2ContainerCredentialsProviderWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class SqsCredentialsConfig {

    @Value("${aws.accessKeyId}")
    private String accessKeyId;

    @Value("${aws.privateKey}")
    private String privateKey;

    @Profile("local")
    @Bean
    public AWSCredentialsProvider provideLocalCredentials() {
        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKeyId, privateKey));
    }

    @Profile("dev")
    @Bean
    public AWSCredentialsProvider provideDevCredentials() {
        return new EC2ContainerCredentialsProviderWrapper();
    }
}

