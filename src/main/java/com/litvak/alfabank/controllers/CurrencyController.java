package com.litvak.alfabank.controllers;

import com.litvak.alfabank.services.AppService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class CurrencyController {
    private final AppService currencyService;

    @GetMapping()
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("/currency/{code}")
    public String getIndexPageWithCurrency(Model model, @PathVariable String code) {
        model.addAttribute("url", currencyService.getGif(code.toUpperCase()).getData().get("image_original_url").toString());
        return "index";
    }
}
