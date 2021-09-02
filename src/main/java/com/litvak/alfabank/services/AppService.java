package com.litvak.alfabank.services;

import com.litvak.alfabank.entities.GifEntity;
import com.litvak.alfabank.utils.Cache;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AppService {
    private final CurrencyService currencyService;
    private final GifService gifService;
    @Value("${currency.rub}")
    private String RUBLE;
    @Value("${currency.broke}")
    private String BROKE;
    @Value("${currency.rich}")
    private String RICH;
    @Value("${currency.oops}")
    private String OOPS;

    public GifEntity getGif(String currency) {

        if (!Cache.cacheMap.containsKey(LocalDate.now())) {
            Cache.cacheMap.put(LocalDate.now(), currencyService.findAllCurrencyRates(LocalDate.now().toString()).getRates());
        }

        if (!Cache.cacheMap.containsKey(LocalDate.now().minusDays(1))) {
            Cache.cacheMap.put(LocalDate.now().minusDays(1), currencyService.findAllCurrencyRates(LocalDate.now().minusDays(1).toString()).getRates());
        }

        Map<String, Double> todayRates = Cache.cacheMap.get(LocalDate.now());
        Map<String, Double> yesterdayRates = Cache.cacheMap.get(LocalDate.now().minusDays(1));

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
