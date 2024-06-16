package com.finacplus.assetpricing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AssetPricingService {

    private static final Logger logger = LoggerFactory.getLogger(AssetPricingService.class);


    @Autowired
    private KafkaTemplate<String, AssetPriceUpdate> kafkaTemplate;

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    @Scheduled(fixedRate = 600000) // Every 10 minutes
    public void fetchAndPublishAssetPrices() {
        logger.info("Fetching and publishing asset prices...");

        List<AssetPriceUpdate> assetPrices = Arrays.asList(
                new AssetPriceUpdate("stock", "AAPL", 150.25),
                new AssetPriceUpdate("stock", "GOOGL", 2800.50),
                new AssetPriceUpdate("mutual fund", "Vanguard 500", 340.75),
                new AssetPriceUpdate("mutual fund", "ParagParikh Multicap Fund", 190)
        );
        assetPrices.forEach(assetPrice -> kafkaTemplate.send(topicName, assetPrice));

        logger.info("Asset prices published successfully.");
    }
}

