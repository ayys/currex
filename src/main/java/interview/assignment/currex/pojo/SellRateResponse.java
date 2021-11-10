package interview.assignment.currex.pojo;

public class SellRateResponse {
    private String currency, date;
    private double rate;

    SellRateResponse() {}

    public SellRateResponse(String currency, String date, double rate) {
        this.currency = currency;
        this.date = date;
        this.rate = rate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
