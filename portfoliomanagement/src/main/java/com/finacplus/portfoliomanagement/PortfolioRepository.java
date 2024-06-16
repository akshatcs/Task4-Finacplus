package com.finacplus.portfoliomanagement;

import com.finacplus.portfoliomanagement.entities.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    Optional<Portfolio> findByUserId(String userId);
}