package futbol.competitions.infraestructure.outbound;

import futbol.competitions.domain.DTO.CompetitionResponse;
import futbol.competitions.domain.exceptions.CompetitionException;
import futbol.competitions.domain.exceptions.CompetitionNotFoundException;
import futbol.competitions.domain.exceptions.CompetitionServerErrorException;
import futbol.competitions.domain.model.Competition;
import futbol.competitions.core.APIkeyFootballConfig;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CompetitionClient {

    private final WebClient webClient;
    private final APIkeyFootballConfig apiConfig;

    public CompetitionClient(APIkeyFootballConfig apiConfig, WebClient.Builder webClientBuilder) {
        this.apiConfig = apiConfig;
        this.webClient = webClientBuilder.baseUrl(apiConfig.getApiUrl()).build();
    }

    public Flux<Competition> getCompetitions() {
        return webClient.get()
                .uri("/competitions")
                .header("X-Auth-Token", apiConfig.getApiKey())
                .retrieve()
                .bodyToMono(CompetitionResponse.class)
                .flatMapMany(response -> Flux.fromIterable(response.getCompetitions()))
                .onErrorResume(WebClientResponseException.class, ex -> {
                    if (ex.getStatusCode().is4xxClientError()) {
                        return Flux.error(new CompetitionException("Client error: " + ex.getMessage()));
                    } else if (ex.getStatusCode().is5xxServerError()) {
                        return Flux.error(new CompetitionServerErrorException("Server error: " + ex.getMessage()));
                    } else {
                        return Flux.error(new CompetitionException("Unexpected error: " + ex.getMessage()));
                    }
                });
    }

    public Mono<Competition> getCompetitionById(String id) {
        return webClient.get()
                .uri("/competitions/{id}", id)
                .header("X-Auth-Token", apiConfig.getApiKey())
                .retrieve()
                .bodyToMono(Competition.class)
                .onErrorResume(WebClientResponseException.class, ex -> {
                    if (ex.getStatusCode().is4xxClientError()) {
                        if (ex.getStatusCode().value() == 404) {
                            return Mono.error(new CompetitionNotFoundException("Competition not found"));
                        } else {
                            return Mono.error(new CompetitionException("Client error: " + ex.getMessage()));
                        }
                    } else if (ex.getStatusCode().is5xxServerError()) {
                        return Mono.error(new CompetitionServerErrorException("Server error: " + ex.getMessage()));
                    } else {
                        return Mono.error(new CompetitionException("Unexpected error: " + ex.getMessage()));
                    }
                });
    }
}
