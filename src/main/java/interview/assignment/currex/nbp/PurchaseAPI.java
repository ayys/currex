package interview.assignment.currex.nbp;

import interview.assignment.currex.entities.SellRate;
import interview.assignment.currex.exceptions.RateNotFoundException;
import interview.assignment.currex.pojo.PurchaseInput;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PurchaseAPI {
    public Map<String, SellRate> getRates(List<PurchaseInput> purchases, String date) {

        Map<String, SellRate> rates = new HashMap<>();
        for (PurchaseInput purchase:
             purchases) {
            rates.put(purchase.getCurrency(), getRate(purchase, date));
        }
        return rates;
    }

    public SellRate getRate(PurchaseInput purchase, String date) throws RateNotFoundException{
        RestTemplate template = new RestTemplate();
        SellRate sellRate = new SellRate(purchase.getCurrency(), -1, date, "a");
        String uri = "https://api.nbp.pl/api/exchangerates/rates/a/" + purchase.getCurrency() + "/" + date;
        double midRate = 0;
        try {
            midRate = template.getForObject(uri, TableAResponse.class).getRates().get(0).getMid();

        } catch (HttpClientErrorException ex) {
            throw new RateNotFoundException(purchase.getCurrency(), date, "a");
        }
        sellRate.setRate(midRate);
        return sellRate;
    }

    }
