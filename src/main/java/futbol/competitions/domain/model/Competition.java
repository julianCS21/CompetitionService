package futbol.competitions.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class Competition {
    private Long id;
    private Area area;
    private String name;
    private String code;
    private String type;
    private String emblem;
    private String plan;
    private Season currentSeason;
    private List<Season> seasons;
    private String lastUpdated;
}