package com.litvak.alfabank.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AppService {
    private final CurrencyService currencyService;
    private final GifService gifService;
    private final String RUBLE = "RUB";
    private final String BROKE = "broke";
    private final String RICH = "rich";
    private final String OOPS = "404";

    public MyGiff getGif(String currency) {
        Map<String, Double> todayRates = currencyService.findAllCurrencyRates(LocalDate.now().toString()).getRates();
        Map<String, Double> yesterdayRates = currencyService.findAllCurrencyRates(LocalDate.now().minusDays(1).toString()).getRates();

        if (todayRates.get(currency) == null || yesterdayRates.get(currency) == null) {
            return gifService.findGif(OOPS);
        }

        if (Double.compare((todayRates.get(currency) / todayRates.get(RUBLE)),
                (yesterdayRates.get(currency) / yesterdayRates.get(RUBLE))) > 0) {
            return gifService.findGif(RICH);
        }
        return gifService.findGif(BROKE);
    }
}
