package com.popkovalex.discogs_stats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DiscogsStatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscogsStatsApplication.class, args);
	}

}
