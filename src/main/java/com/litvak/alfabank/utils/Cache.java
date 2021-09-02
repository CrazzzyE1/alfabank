package com.litvak.alfabank.utils;

import lombok.Getter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Getter
public class Cache {
    public static Map<LocalDate, Map<String, Double>> cacheMap = new HashMap<>();
}
