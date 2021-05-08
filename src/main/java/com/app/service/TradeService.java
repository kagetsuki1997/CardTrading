package com.app.service;

import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

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
import com.app.model.TradeRecord;
import com.app.model.User;
import com.app.repository.TradeRepository;

@Service
public class TradeService {
	private static Logger Log = LoggerFactory.getLogger(TradeService.class);
	@Value("${pageSize.default}")
	private int pageSize;
	private TradeRepository tradeRepository;
	@Autowired
	private TradeRecordService tradeRecordService;
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	public TradeService(TradeRepository tradeRepository) {
		this.tradeRepository = tradeRepository;
	}

	public List<Trade> findAll() {
		return tradeRepository.findAll();
	}

	public List<Trade> findTop() {
		int page = 0, size = pageSize;
		Pageable pageable = PageRequest.of(page, size, Sort.by("createTime").descending());
		return tradeRepository.findAll(pageable).getContent();
	}

	public List<Trade> findByTrader(User trader) {
		int page = 0, size = pageSize;
		Pageable pageable = PageRequest.of(page, size, Sort.by("createTime").descending());
		return tradeRepository.findByTrader(trader, pageable);
	}

	public List<Trade> findByCard(Card card) {
		int page = 0, size = pageSize;
		Pageable pageable = PageRequest.of(page, size, Sort.by("createTime").descending());
		return tradeRepository.findByTradeCard(card, pageable);
	}

	@Transactional
	public Trade saveTrade(Trade trade) {
		Log.info("Save Trade: " + trade.getTrader().getName() + " " + trade.getAction() + " "
				+ trade.getTradeCard().getName() + " for " + trade.getPrice() + "　USD.");
		return makeDeal(tradeRepository.save(trade));
	}

	public void makeAllDeal() {
		List<Trade> tradeList = findAll();
		for (int i = 0; i < tradeList.size(); i++) {
			Optional<Trade> optTrade = tradeRepository.findById(tradeList.get(i).getId());
			if (optTrade.isPresent()) {
				Trade trade = optTrade.get();
				//Log.info("Deal with "+trade.getId()+" "+trade.getAction()+" "+trade.getTradeCard().getName()+trade.getPrice());
				Trade targetTrade = null;
				TradeRecord tradeRecord=null;
				if (trade.getAction().equals("buy")) {
					targetTrade = findASellTrade(trade);
					if(targetTrade!=null)
						tradeRecord = tradeRecordService.saveTradeRecord(targetTrade,trade,targetTrade.getPrice());
				} else {
					targetTrade = findABuyTrade(trade);
					if(targetTrade!=null)
						tradeRecord = tradeRecordService.saveTradeRecord(trade,targetTrade,targetTrade.getPrice());
				}
				if (tradeRecord != null) {
					trade.setCompleted(true);
					trade.setCompleteTime(tradeRecord.getCompleteTime());
					trade.setTradeRecord(tradeRecord);
					targetTrade.setCompleted(true);
					targetTrade.setCompleteTime(tradeRecord.getCompleteTime());
					targetTrade.setTradeRecord(tradeRecord);
					saveTrade(trade);
					saveTrade(targetTrade);
				}
			}
		}
	}
	public Trade makeDeal(Trade trade) {
		Trade targetTrade = null;
		TradeRecord tradeRecord=null;
		if (trade.getAction().equals("buy")) {
			targetTrade = findASellTrade(trade);
			if(targetTrade!=null)
				tradeRecord = tradeRecordService.saveTradeRecord(targetTrade,trade,trade.getPrice());
		} else {
			targetTrade = findABuyTrade(trade);
			if(targetTrade!=null)
				tradeRecord = tradeRecordService.saveTradeRecord(trade,targetTrade,trade.getPrice());
		}
		if (tradeRecord != null) {
			trade.setCompleted(true);
			trade.setCompleteTime(tradeRecord.getCompleteTime());
			trade.setTradeRecord(tradeRecord);
			targetTrade.setCompleted(true);
			targetTrade.setCompleteTime(tradeRecord.getCompleteTime());
			targetTrade.setTradeRecord(tradeRecord);
			trade=saveTrade(trade);
			saveTrade(targetTrade);
		}
		return trade;
	}

	public Trade findASellTrade(Trade trade) {
		Trade targetTrade = null;
		List<Trade> buyTradeList = tradeRepository.findBuyTrade(trade.getId(), trade.getTradeCard().getId(),
				trade.getPrice());
		if (!buyTradeList.isEmpty()) {
			targetTrade = buyTradeList.get(0);
		}
		return targetTrade;
	}

	public Trade findABuyTrade(Trade trade) {
		Trade targetTrade = null;
		List<Trade> sellTradeList = tradeRepository.findSellTrade(trade.getId(), trade.getTradeCard().getId(),
				trade.getPrice());
		if (!sellTradeList.isEmpty()) {
			targetTrade = sellTradeList.get(0);
		}
		return targetTrade;
	}
}
