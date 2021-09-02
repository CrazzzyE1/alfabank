package com.litvak.alfabank.services;

import com.litvak.alfabank.entities.ChangeRateEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "currencySearch", url = "${currency.api.url}")
public interface CurrencyService {
    @RequestMapping(value = "/{today}.json?app_id=${currency.api.key}", method = RequestMethod.GET)
    ChangeRateEntity findAllCurrencyRates(@PathVariable String today);
}
