package com.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class BackgroundService {
	private static Logger Log=LoggerFactory.getLogger(BackgroundService.class);
	@Autowired
	private TradeService tradeService;
	
	@Scheduled(fixedRateString="${backgroundMakeAllDealInterval}")
    public void backgroundMakeAllDeal() {
		Log.info("--Background MakeAllDeal");
		tradeService.makeAllDeal();
    }
}
