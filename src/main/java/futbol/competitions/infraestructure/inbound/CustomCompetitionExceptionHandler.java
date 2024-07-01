package futbol.competitions.infraestructure.inbound;



import futbol.competitions.domain.exceptions.CompetitionException;
import futbol.competitions.domain.exceptions.CompetitionNotFoundException;
import futbol.competitions.domain.exceptions.CompetitionServerErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class CustomCompetitionExceptionHandler {

    @ExceptionHandler(CompetitionNotFoundException.class)
    public final ResponseEntity<String> handleCompetitionNotFoundException(CompetitionNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CompetitionServerErrorException.class)
    public final ResponseEntity<String> handleCompetitionServerErrorException(CompetitionServerErrorException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CompetitionException.class)
    public final ResponseEntity<String> handleCompetitionException(CompetitionException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<String> handleAllExceptions(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
