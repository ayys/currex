package interview.assignment.currex.advices;


import interview.assignment.currex.exceptions.RateNotFoundException;
import interview.assignment.currex.pojo.RateNotFoundResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RateNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(RateNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    RateNotFoundResponse rateNotFoundHandler(RateNotFoundException ex) {
        return new RateNotFoundResponse(ex.getMessage());
    }
}
