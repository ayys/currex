package interview.assignment.currex.nbp;

import java.util.List;

public class TableAResponse {
    private List<TableARate> rates;

    TableAResponse () {};

    public List<TableARate> getRates() {
        return rates;
    }

    public void setRates(List<TableARate> rates) {
        this.rates = rates;
    }
}
