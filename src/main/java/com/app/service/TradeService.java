package com.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Trade;
import com.app.repository.TradeRepository;

@Service
public class TradeService {
	private static Logger Log=LoggerFactory.getLogger(TradeService.class);
	private TradeRepository tradeRepository;
	
	@Autowired
	public TradeService(TradeRepository tradeRepository) {
		this.tradeRepository=tradeRepository;
	}
	
	public Trade saveTrade(Trade trade) {
		Log.info("Save Trade: "+trade.getTrader().getName()+" "+trade.getAction()+" "+trade.getTradeCard().getName()+" for "+trade.getPrice()+"　USD.");
		return tradeRepository.save(trade);
	}
}
