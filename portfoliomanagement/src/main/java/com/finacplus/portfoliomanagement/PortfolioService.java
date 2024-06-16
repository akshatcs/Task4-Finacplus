package com.finacplus.portfoliomanagement;

import com.finacplus.portfoliomanagement.entities.Asset;
import com.finacplus.portfoliomanagement.entities.AssetPriceUpdate;
import com.finacplus.portfoliomanagement.entities.Portfolio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private RedisTemplate<String, Portfolio> redisTemplate;

    public Portfolio getPortfolioByUserId(String userId) {
        String cacheKey = "portfolio:" + userId;
        ValueOperations<String, Portfolio> ops = redisTemplate.opsForValue();
        if (redisTemplate.hasKey(cacheKey)) {
            return ops.get(cacheKey);
        } else {
            Portfolio portfolio = portfolioRepository.findByUserId(userId).orElse(null);
            if (portfolio != null) {
                ops.set(cacheKey, portfolio, Duration.ofMinutes(10));
            }
            return portfolio;
        }
    }

    @Transactional
    public void updatePortfolio(AssetPriceUpdate assetPriceUpdate) {
        List<Portfolio> portfolios = portfolioRepository.findAll();
        for (Portfolio portfolio : portfolios) {
            boolean updated = false;
            for (Asset asset : portfolio.getAssets()) {
                if (asset.getAssetType().equals(assetPriceUpdate.getAssetType()) &&
                        asset.getAssetName().equals(assetPriceUpdate.getAssetName())) {
                    asset.setPrice(assetPriceUpdate.getPrice());
                    updated = true;
                }
            }
            if (updated) {
                portfolioRepository.save(portfolio);
                redisTemplate.opsForValue().set("portfolio:" + portfolio.getUserId(), portfolio, Duration.ofMinutes(10));
            }
        }
    }
}
