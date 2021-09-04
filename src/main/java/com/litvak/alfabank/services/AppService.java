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

    /**
     * Метод возвращает полученный от REST API объект картинки
     * Так же сохраняет в кеш полученные курсы валют (для минимизации обращения к сервису курсов валют)
     * @param currency Код валюты
     * @return GifEntity - объект картинки
     * */
    public GifEntity getGif(String currency) {
        if (!Cache.cacheMap.containsKey(LocalDate.now())) {
            Cache.cacheMap.put(LocalDate.now(), currencyService.findAllCurrencyRates(LocalDate.now().toString()).getRates());
        }

        if (!Cache.cacheMap.containsKey(LocalDate.now().minusDays(1))) {
            Cache.cacheMap.put(LocalDate.now().minusDays(1), currencyService.findAllCurrencyRates(LocalDate.now().minusDays(1).toString()).getRates());
        }

        return gifService.findGif(getCondition(Cache.cacheMap.get(LocalDate.now()), Cache.cacheMap.get(LocalDate.now().minusDays(1)), currency));
    }

    /**
     * Метод корректность входного кода искомой валюты
     * @param todayRates Курсы валют на сегодня
     * @param yesterdayRates Курсы валют на завтра
     * @param currency Код валюты
     * @return true - код валюты неверный или отсутствует, false - код валюты корректный
     * */
    private boolean checkWrongCurrencyCode(Map<String, Double> todayRates, Map<String, Double> yesterdayRates, String currency) {
        return (todayRates.get(currency) == null || yesterdayRates.get(currency) == null);
    }

    /**
     * Метод возвращает статус картинки для запроса в REST API сервиса картинок
     * @param todayRates Курсы валют на сегодня
     * @param yesterdayRates Курсы валют на завтра
     * @param currency Код валюты
     * @return Возвращает статус для поиска каринки
     * */
    private String getCondition(Map<String, Double> todayRates, Map<String, Double> yesterdayRates, String currency) {
        if (checkWrongCurrencyCode(todayRates, yesterdayRates, currency)) {
            return Cache.status = OOPS;
        }
        if (Double.compare((todayRates.get(currency) / todayRates.get(RUBLE)), (yesterdayRates.get(currency) / yesterdayRates.get(RUBLE))) > 0) {
            return Cache.status = RICH;
        }

        return Cache.status = BROKE;
    }
}
