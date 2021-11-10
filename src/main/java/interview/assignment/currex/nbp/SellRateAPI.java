package interview.assignment.currex.nbp;

import interview.assignment.currex.entities.SellRate;
import interview.assignment.currex.exceptions.RateNotFoundException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class SellRateAPI {
    public SellRate getRate(String currency, String date) throws RateNotFoundException {
        SellRate sellRate = new SellRate(currency, -1, date, "c");
        String uri = "https://api.nbp.pl/api/exchangerates/rates/c/" + currency + "/" + date;
        RestTemplate template = new RestTemplate();
        double askRate;
        try {
            askRate = template.getForObject(uri, TableCResponse.class).getRates().get(0).getAsk();
        } catch (HttpClientErrorException ex) {
            throw new RateNotFoundException(currency, date, "c");
        }
        sellRate.setRate(askRate);
        return sellRate;
    }
}
