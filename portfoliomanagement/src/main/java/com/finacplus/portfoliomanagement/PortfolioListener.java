package com.finacplus.portfoliomanagement;

import com.finacplus.portfoliomanagement.entities.AssetPriceUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PortfolioListener {

    private static final Logger logger = LoggerFactory.getLogger(PortfolioListener.class);

    @Autowired
    private PortfolioService portfolioService;

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "portfolio-group")
    public void handleAssetPriceUpdate(AssetPriceUpdate assetPriceUpdate) {
        try {
            logger.info("Received asset price update: {}", assetPriceUpdate);

            portfolioService.updatePortfolio(assetPriceUpdate);

            logger.info("Portfolio updated successfully for asset: {} - {}", assetPriceUpdate.getAssetType(), assetPriceUpdate.getAssetName());

        } catch (Exception e){
            logger.error("Failed to handle asset price update: {}", e.getMessage());
        }
    }
}
