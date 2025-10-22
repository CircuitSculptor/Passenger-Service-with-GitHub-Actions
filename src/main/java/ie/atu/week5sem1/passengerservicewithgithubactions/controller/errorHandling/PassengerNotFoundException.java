package ie.atu.week5sem1.passengerservicewithgithubactions.controller.errorHandling;

public class PassengerNotFoundException extends RuntimeException
{
    private String message;
    private String field;
    public PassengerNotFoundException(String field, String message)
    {
        this.field = field;
        this.message = message;
    }
    public PassengerNotFoundException(String message) {
        super(message);
    }
}
