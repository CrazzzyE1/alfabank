package com.litvak.alfabank.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl {
    private final CurrencyService currencyService;
    private final String RUBLE = "RUB";

    public ChangeRate getGif(String currency) {
        Map<String, Double> todayRates = currencyService.findAllCurrencyRates(LocalDate.now().toString()).getRates();
        Map<String, Double> yesterdayRates = currencyService.findAllCurrencyRates(LocalDate.now().minusDays(1).toString()).getRates();
        Double currencyRateToday = todayRates.get(currency);
        Double rubleRateToday = todayRates.get(RUBLE);
        Double currencyRateYesterday = yesterdayRates.get(currency);
        Double rubleRateYesterday = yesterdayRates.get(RUBLE);
        if (currencyRateToday == null || currencyRateYesterday == null) {
            return null;
        }
        System.out.println(currency + "     today: " + currencyRateToday + " " + RUBLE + " " + rubleRateToday);
        System.out.println(currency + " yesterday: " + currencyRateYesterday + " " + RUBLE + " " + rubleRateYesterday);
        System.out.println(currencyRateToday / rubleRateToday + "/" + currencyRateYesterday / rubleRateYesterday);
        // Если 1 то курс вырос относительно рубля
        System.out.println(Double.compare((currencyRateToday / rubleRateToday), (currencyRateYesterday / rubleRateYesterday)));
        return currencyService.findAllCurrencyRates(LocalDate.now().toString());
    }
}
