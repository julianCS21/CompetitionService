package futbol.competitions.domain.exceptions;

public class CompetitionServerErrorException extends CompetitionException {
    public CompetitionServerErrorException(String message) {
        super(message);
    }

}