package com.finacplus.portfoliomanagement.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssetPriceUpdate {
    private String assetType;
    private String assetName;
    private double price;
}
