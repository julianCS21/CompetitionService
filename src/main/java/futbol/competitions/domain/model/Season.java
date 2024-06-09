package futbol.competitions.domain.model;

import lombok.Data;

@Data
public  class Season {
    private Long id;
    private String startDate;
    private String endDate;
    private Integer currentMatchday;
    private Winner winner;
}