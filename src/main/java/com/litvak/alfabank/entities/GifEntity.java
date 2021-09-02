package com.litvak.alfabank.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GifEntity {
    private Map<String, Object> data;
    private Map<String, Object> meta;
}
