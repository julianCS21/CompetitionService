package futbol.competitions.infraestructure.inbound;

import futbol.competitions.application.CompetitionService;
import futbol.competitions.domain.exceptions.CompetitionException;
import futbol.competitions.domain.model.Competition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/competitions")
@CrossOrigin(origins = "*")
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
