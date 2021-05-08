package com.app.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.app.model.Card;
import com.app.model.Trade;
import com.app.model.User;
import com.app.repository.TradeRepository;

@Service
public class TradeService {
	private static Logger Log=LoggerFactory.getLogger(TradeService.class);
	@Value("${pageSize.default}")
	private int pageSize;
	private TradeRepository tradeRepository;
	
	@Autowired
	public TradeService(TradeRepository tradeRepository) {
		this.tradeRepository=tradeRepository;
	}
	
	public List<Trade> findAll(){
		int page=0,size=pageSize;
		Pageable pageable=PageRequest.of(page, size, Sort.by("createTime").descending());
		return tradeRepository.findAll(pageable).getContent();
	}
	
	public List<Trade> findByTrader(User trader){
		int page=0,size=pageSize;
		Pageable pageable=PageRequest.of(page, size, Sort.by("createTime").descending());
		return tradeRepository.findByTrader(trader, pageable);
	}
	
	public List<Trade> findByCard(Card card){
		int page=0,size=pageSize;
		Pageable pageable=PageRequest.of(page, size, Sort.by("createTime").descending());
		return tradeRepository.findByTradeCard(card, pageable);
	}
	
	public Trade saveTrade(Trade trade) {
		Log.info("Save Trade: "+trade.getTrader().getName()+" "+trade.getAction()+" "+trade.getTradeCard().getName()+" for "+trade.getPrice()+"　USD.");
		return tradeRepository.save(trade);
	}
}
