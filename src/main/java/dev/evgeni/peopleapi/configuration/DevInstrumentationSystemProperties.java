package dev.evgeni.peopleapi.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import lombok.Data;

@Profile("dev")
@Configuration
@ConfigurationProperties(prefix = "mylogging")
@Data
public class DevInstrumentationSystemProperties {

    private String username;
    private String password;

}
