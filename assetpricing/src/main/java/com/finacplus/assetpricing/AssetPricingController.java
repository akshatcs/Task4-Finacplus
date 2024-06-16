package com.finacplus.assetpricing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pricing")
public class AssetPricingController {

    @Autowired
    private AssetPricingService assetPricingService;

    @GetMapping("/publish")
    public ResponseEntity<String> publishPrices() {
        assetPricingService.fetchAndPublishAssetPrices();
        return ResponseEntity.ok("Asset prices published successfully.");
    }
}

