package com.litvak.alfabank.rest;

import com.litvak.alfabank.entities.GifEntity;
import com.litvak.alfabank.services.AppService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CurrencyControllerRest {
    private final AppService currencyService;

    @GetMapping("/{currency}")
    public GifEntity getGif(@PathVariable String currency) {
        return currencyService.getGif(currency.toUpperCase());
    }
}
