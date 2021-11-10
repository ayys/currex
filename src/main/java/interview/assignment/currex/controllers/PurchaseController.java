package interview.assignment.currex.controllers;


import interview.assignment.currex.entities.SellRate;
import interview.assignment.currex.exceptions.RateNotFoundException;
import interview.assignment.currex.nbp.PurchaseAPI;
import interview.assignment.currex.nbp.SellRateAPI;
import interview.assignment.currex.pojo.Purchase;
import interview.assignment.currex.pojo.PurchaseInput;
import interview.assignment.currex.pojo.PurchaseResponse;
import interview.assignment.currex.pojo.SellRateResponse;
import interview.assignment.currex.repositories.SellRateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PurchaseController {
    private static final Logger log = LoggerFactory.getLogger(PurchaseController.class);
    private final SellRateRepository sellRateRepository;

    public PurchaseController(SellRateRepository sellRateRepository) {
        this.sellRateRepository = sellRateRepository;
    }


    @GetMapping("/sell-rates/{currency}/{date}")
    SellRateResponse sellRates(@PathVariable String currency, @PathVariable String date) {
        SellRate sellRate;
        List<SellRate> sellRates = sellRateRepository.findAllSellRateByCurrencyAndDateAndTable(currency, date, "c");

        // if the sell rate isn't in cache, fetch it from API
        if (sellRates.isEmpty()) {
            SellRateAPI api = new SellRateAPI();
            sellRate = api.getRate(currency, date);
            sellRateRepository.save(sellRate);
        } else {
            sellRate = sellRates.get(0);
        }
        return new SellRateResponse(sellRate.getCurrency(), sellRate.getDate(), sellRate.getRate());
    }

    @PostMapping("/purchase-cost/{date}")
    PurchaseResponse totalPurchases(@RequestBody List<PurchaseInput> purchaseInputs, @PathVariable String date) {
        List<String> currencyCodes = new ArrayList<>();
        List<Purchase> purchases = new ArrayList<>();

        for (PurchaseInput purchaseInput :
                purchaseInputs) {
            currencyCodes.add(purchaseInput.getCurrency());
            List<SellRate> sellRates = sellRateRepository.findAllSellRateByCurrencyAndDateAndTable(purchaseInput.getCurrency(), date, "a");
            Purchase purchase = new Purchase(-1, purchaseInput.getAmount(), purchaseInput.getCurrency(), date);
            if (sellRates.isEmpty()) {
                log.info("Calling API");
                PurchaseAPI api = new PurchaseAPI();

                SellRate sellRate = api.getRate(purchaseInput, date);
                purchase.setRate(sellRate.getRate());
                sellRateRepository.save(sellRate);
            } else {
                purchase.setRate(sellRates.get(0).getRate());
            }
            purchases.add(purchase);
        }

        double totalAmount = 0;
        for (Purchase purchase:
             purchases) {
            totalAmount += purchase.getAmount() * purchase.getRate();
        }
        return new PurchaseResponse(totalAmount);
    }
}
