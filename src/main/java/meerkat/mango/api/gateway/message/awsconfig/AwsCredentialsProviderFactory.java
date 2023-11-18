package meerkat.mango.api.gateway.message.awsconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;

@Configuration
public class AwsCredentialsProviderFactory {

    private final String awsAccessKey;
    private final String awsSecretKey;

    public AwsCredentialsProviderFactory(@Value("${aws.access.key}") String awsAccessKey,
                                         @Value("${aws.secret.key}") String awsSecretKey) {
        this.awsAccessKey = awsAccessKey;
        this.awsSecretKey = awsSecretKey;
    }

    @Bean
    @Profile("dev")
    public AwsCredentialsProvider provideAwsDevCredentials() {
        return StaticCredentialsProvider.create(AwsBasicCredentials.create(awsAccessKey, awsSecretKey));
    }

    /*
        Creates the credentials from environment variables when running on EC2 instances
     */
    @Bean
    public AwsCredentialsProvider provideAwsCredentials() {
        return EnvironmentVariableCredentialsProvider.create();
    }
}
