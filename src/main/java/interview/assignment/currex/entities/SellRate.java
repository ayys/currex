package interview.assignment.currex.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public
class SellRate {
    private @Id @GeneratedValue Long id;
    private String currency;
    private String date;
    private String dataTable;
    private double rate;

    SellRate() {}

    public SellRate(String currency, double rate, String date, String dataTable) {
        this.currency = currency;
        this.rate = rate;
        this.date = date;
        this.dataTable = dataTable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTable() {
        return dataTable;
    }

    public void setTable(String dataTable) {
        this.dataTable = dataTable;
    }
}
