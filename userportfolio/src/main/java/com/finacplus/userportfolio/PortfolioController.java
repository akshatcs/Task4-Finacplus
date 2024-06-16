package com.finacplus.userportfolio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

    private static final Logger logger = LoggerFactory.getLogger(PortfolioController.class);

    @Autowired
    private PortfolioService portfolioService;

    @GetMapping("/{userId}")
    public ResponseEntity<Portfolio> getPortfolioByUserId(@PathVariable String userId) {
        logger.info("Request received to fetch portfolio for userId: {}", userId);

        Portfolio portfolio = portfolioService.getPortfolioByUserId(userId);
        if (portfolio != null) {
            logger.info("Returning portfolio for userId: {}", userId);
            return ResponseEntity.ok(portfolio);
        } else {
            logger.warn("Portfolio not found for userId: {}", userId);
            return ResponseEntity.notFound().build();
        }
    }
}
