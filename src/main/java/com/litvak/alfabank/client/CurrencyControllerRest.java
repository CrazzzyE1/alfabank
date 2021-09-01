package com.litvak.alfabank.client;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CurrencyControllerRest {
    private final CurrencyServiceImpl currencyService;

    @GetMapping("/{currency}")
    public ChangeRate getGif(@PathVariable String currency) {
        return currencyService.getGif(currency.toUpperCase());
    }
}
