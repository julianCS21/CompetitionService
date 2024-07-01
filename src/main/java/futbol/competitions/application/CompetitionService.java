package futbol.competitions.application;

import futbol.competitions.domain.model.Competition;
import futbol.competitions.domain.DTO.CompetitionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import futbol.competitions.infraestructure.outbound.CompetitionClient;

@Service
public class CompetitionService {

    private final CompetitionClient competitionClient;

    @Autowired
    public CompetitionService(CompetitionClient competitionClient) {
        this.competitionClient = competitionClient;
    }

    public Flux<Competition> getCompetitions() {
        return competitionClient.getCompetitions();
    }



    public Mono<Competition> getCompetitionById(String id) {
        return competitionClient.getCompetitionById(id);
    }


}
