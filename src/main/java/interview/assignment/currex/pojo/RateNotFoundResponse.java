package interview.assignment.currex.pojo;

public class RateNotFoundResponse {
    private String message;

    RateNotFoundResponse() {};

    public RateNotFoundResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
