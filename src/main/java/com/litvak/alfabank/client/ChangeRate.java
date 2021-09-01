package com.litvak.alfabank.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeRate {
    private String disclaimer;
    private String license;
    private BigInteger timestamp;
    private String base;
    private Map<String, Double> rates;
}
