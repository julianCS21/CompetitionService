package futbol.competitions.application;

import futbol.competitions.domain.model.Competition;
import futbol.competitions.domain.DTO.CompetitionResponse;
import futbol.competitions.infraestructure.outbound.APIkeyFootballConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CompetitionService {

    private final APIkeyFootballConfig apiConfig;
    private final WebClient webClient;

    @Autowired
    public CompetitionService(APIkeyFootballConfig apiConfig, WebClient.Builder webClientBuilder) {
        this.apiConfig = apiConfig;
        this.webClient = webClientBuilder.baseUrl(apiConfig.getApiUrl()).build();
    }

    public Flux<Competition> getCompetitions() {
        return webClient.get()
                .uri("/competitions")
                .header("X-Auth-Token", apiConfig.getApiKey())
                .retrieve()
                .bodyToMono(CompetitionResponse.class)
                .flatMapMany(response -> Flux.fromIterable(response.getCompetitions()));
    }

    public Mono<Competition> getCompetitionById(String id) {
        return webClient.get()
                .uri("/competitions/{id}", id)
                .header("X-Auth-Token", apiConfig.getApiKey())
                .retrieve()
                .bodyToMono(Competition.class);
    }
}
