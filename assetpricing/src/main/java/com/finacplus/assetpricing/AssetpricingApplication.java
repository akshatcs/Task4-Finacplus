package com.finacplus.assetpricing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AssetpricingApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssetpricingApplication.class, args);
	}

}
