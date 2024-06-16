package com.finacplus.assetpricing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssetPriceUpdate {
    private String assetType; // Mutual fund or stocks, etc.
    private String assetName;
    private double price;
}
