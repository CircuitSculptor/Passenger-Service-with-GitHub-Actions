package ie.atu.week5sem1.passengerservicewithgithubactions.controller.errorHandling;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandling {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String showErrorDetails(MethodArgumentNotValidException mae)
    {
        return "There is an issue" + mae.getBindingResult().getFieldError().getDefaultMessage();
    }
}
