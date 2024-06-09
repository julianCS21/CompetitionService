package futbol.competitions.infraestructure.inbound;

import futbol.competitions.application.CompetitionService;
import futbol.competitions.domain.model.Competition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/competitions")
public class CompetitionController {

    private final CompetitionService competitionService;

    @Autowired
    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @GetMapping
    public Flux<Competition> getAllCompetitions() {
        return competitionService.getCompetitions();
    }
    @GetMapping("/{id}")
    public Mono<Competition> getCompetitionById(@PathVariable String id) {
        return competitionService.getCompetitionById(id);
    }
}