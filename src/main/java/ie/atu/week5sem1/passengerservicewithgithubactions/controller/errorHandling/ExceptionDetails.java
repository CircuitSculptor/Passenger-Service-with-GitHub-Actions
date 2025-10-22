package ie.atu.week5sem1.passengerservicewithgithubactions.controller.errorHandling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionDetails {
    private String fieldName;
    private String fieldValue;
}
