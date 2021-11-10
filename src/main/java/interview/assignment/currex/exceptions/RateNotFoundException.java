package interview.assignment.currex.exceptions;

public class RateNotFoundException extends RuntimeException{
    public RateNotFoundException(String currency, String date, String tableName) {
        super("Could not fetch data for country code " + currency + " from " + date + " in table " + tableName);
    }
}
