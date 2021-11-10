package interview.assignment.currex.pojo;

public class Purchase {
    private double rate, amount;
    private String currency, date;

    Purchase() {}

    public Purchase(double rate, double amount, String currency, String date) {
        this.rate = rate;
        this.amount = amount;
        this.currency = currency;
        this.date = date;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
