package com.litvak.alfabank.client;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CurrencyControllerRest {
    private final AppService currencyService;

    @GetMapping("/{currency}")
    public String getGif(@PathVariable String currency) {
        return "<img src=" + currencyService.getGif(currency.toUpperCase()).getData().get("image_original_url").toString() + ">";
    }
}
