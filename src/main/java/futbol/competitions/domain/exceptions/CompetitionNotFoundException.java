package futbol.competitions.domain.exceptions;

public class CompetitionNotFoundException extends CompetitionException {
    public CompetitionNotFoundException(String message) {
        super(message);
    }
}