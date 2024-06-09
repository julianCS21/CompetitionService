package futbol.competitions.domain.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import futbol.competitions.domain.model.Competition;
import lombok.Data;
import java.util.List;

@Data
public class CompetitionResponse {
    @JsonProperty("competitions")
    private List<Competition> competitions;
}
