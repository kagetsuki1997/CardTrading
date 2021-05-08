package com.app.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Card;
import com.app.model.Trade;
import com.app.model.User;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Integer>{
	List<Trade> findByTrader(User trader,Pageable pageable);
	List<Trade> findByTradeCard(Card Card,Pageable pageable);
}
