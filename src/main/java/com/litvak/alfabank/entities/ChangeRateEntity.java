package com.litvak.alfabank.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeRateEntity {
    private String disclaimer;
    private String license;
    private BigInteger timestamp;
    private String base;
    private Map<String, Double> rates;
}
