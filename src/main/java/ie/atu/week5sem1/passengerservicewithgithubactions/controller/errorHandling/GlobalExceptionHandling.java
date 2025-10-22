package ie.atu.week5sem1.passengerservicewithgithubactions.controller.errorHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandling {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ExceptionDetails>> showErrorDetails(MethodArgumentNotValidException mae)
    {
        List<ExceptionDetails> errorList = new ArrayList<>();
        for (FieldError fieldError : mae.getBindingResult().getFieldErrors())
        {
            ExceptionDetails exceptionDetails = new ExceptionDetails();
            exceptionDetails.setFieldName(fieldError.getField());
            exceptionDetails.setFieldValue(fieldError.getDefaultMessage());
            errorList.add(exceptionDetails);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorList);
    }

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<ExceptionDetails> showDuplicateError(DuplicateException de)
    {
        ExceptionDetails exceptionDetails = new ExceptionDetails();
        exceptionDetails.setFieldName("Passenger ID");
        exceptionDetails.setFieldValue(de.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionDetails);

    }

    @ExceptionHandler(PassengerNotFoundException.class)
    public String showPassengerNotFoundError(PassengerNotFoundException pe)
    {
        return "Passenger With ID: " + pe.getMessage() + " Not Found";
    }
}
