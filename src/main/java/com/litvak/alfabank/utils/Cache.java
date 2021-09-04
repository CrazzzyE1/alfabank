package com.litvak.alfabank.utils;

import lombok.Getter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Хранит полученные ранее курсы валют и статус возвращаемой картинки
 * */
@Getter
public class Cache {
    public static String status = "";
    public static Map<LocalDate, Map<String, Double>> cacheMap = new HashMap<>();
}
