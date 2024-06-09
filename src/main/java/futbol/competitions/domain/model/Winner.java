package futbol.competitions.domain.model;

import lombok.Data;

@Data
public  class Winner {
    private Long id;
    private String name;
    private String shortName;
    private String tla;
    private String crest;
    private String address;
    private String website;
    private int founded;
    private String clubColors;
    private String venue;
    private String lastUpdated;
}