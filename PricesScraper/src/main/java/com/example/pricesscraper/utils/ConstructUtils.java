package com.example.pricesscraper.utils;

import com.example.pricesscraper.domain.Items;

import java.math.BigDecimal;

public class ConstructUtils {
    public static Items setItems(String name ,String abrasion , BigDecimal price , BigDecimal seekPrice , Integer saleAmount , Integer seekAmount){
        Items item = new Items();
        item.setProductName(name);
        item.setAbrasion(abrasion);
        item.setPrice(price);
        item.setSeekPrice(seekPrice);
        item.setOnSale(saleAmount);
        item.setOnSeek(seekAmount);
        return item;
    }
}
