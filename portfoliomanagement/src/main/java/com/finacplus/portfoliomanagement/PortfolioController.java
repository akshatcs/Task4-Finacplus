package com.finacplus.portfoliomanagement;

import com.finacplus.portfoliomanagement.entities.Portfolio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @GetMapping("/{userId}")
    public ResponseEntity<Portfolio> getPortfolioByUserId(@PathVariable String userId) {

        Portfolio portfolio = portfolioService.getPortfolioByUserId(userId);
        if (portfolio != null) {
            return ResponseEntity.ok(portfolio);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
