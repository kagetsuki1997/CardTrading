package com.app.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.app.model.Card;
import com.app.model.Trade;
import com.app.model.TradeRecord;
import com.app.model.User;
import com.app.repository.TradeRecordRepository;

@Service
public class TradeRecordService {
	private static Logger Log=LoggerFactory.getLogger(TradeRecordService.class);
	@Value("${pageSize.default}")
	private int pageSize;
	private TradeRecordRepository tradeRecordRepository;
	
	@Autowired
	public TradeRecordService(TradeRecordRepository tradeRecordRepository) {
		this.tradeRecordRepository=tradeRecordRepository;
	}
	public List<TradeRecord> findAll(){
		int page=0,size=pageSize;
		Pageable pageable=PageRequest.of(page, size, Sort.by("createTime").descending());
		return tradeRecordRepository.findAll(pageable).getContent();
	}
	public List<TradeRecord> findByTrader(User trader){
		int page=0,size=pageSize;
		Pageable pageable=PageRequest.of(page, size, Sort.by("createTime").descending());
		return tradeRecordRepository.findByTrader(trader.getId(), pageable);
	}
	
	public List<TradeRecord> findByCard(Card card){
		int page=0,size=pageSize;
		Pageable pageable=PageRequest.of(page, size, Sort.by("createTime").descending());
		return tradeRecordRepository.findByTradeCard(card, pageable);
	}
	public Optional<TradeRecord> findByTradeRecordId(Long id) {
		return tradeRecordRepository.findById(id);
	}
	@Transactional
	public TradeRecord saveTradeRecord(Trade sellTrade,Trade buyTrade,Double soldPrice) {
		if(!sellTrade.isCompleted()&&!buyTrade.isCompleted()) {
			TradeRecord tradeRecord=new TradeRecord(sellTrade.getTrader(), buyTrade.getTrader(), buyTrade.getTradeCard(), soldPrice, sellTrade, buyTrade);
			Log.info("Save TradeRecord:"+sellTrade.getTrader().getName()+" "+buyTrade.getTrader().getName()+" "+buyTrade.getTradeCard().getName()+" "+soldPrice);
			return tradeRecordRepository.save(tradeRecord);
		}else
			return null;
	}
}
