package ie.atu.week5sem1.passengerservicewithgithubactions.controller.errorHandling;

public class DuplicateException extends RuntimeException
{
    private String message;
    private String field;
    public DuplicateException(String field, String message)
    {
        this.field = field;
        this.message = message;
    }
    public DuplicateException(String message)
    {
        super(message);
    }
}
