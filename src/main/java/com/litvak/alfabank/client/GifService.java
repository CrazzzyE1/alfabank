package com.litvak.alfabank.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "gifSearch", url = "${gif.api.url}")
public interface GifService {
    @RequestMapping(value = "/random?api_key=${gif.api.key}&tag={condition}", method = RequestMethod.GET)
    MyGiff findGif(@PathVariable String condition);
}
