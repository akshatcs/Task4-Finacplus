package com.finacplus.userportfolio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

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
}
