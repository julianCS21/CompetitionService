package futbol.competitions.core;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class APIkeyFootballConfig {

    @Value("${external.apiFootball.key}")
    private String apiKey;

    @Value("${external.apiFootball.url}")
    private String apiUrl;

}
