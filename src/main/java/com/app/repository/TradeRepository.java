package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.model.Card;
import com.app.model.Trade;
import com.app.model.User;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {
    Optional<Trade> findById(Long Id);

    List<Trade> findByTrader(User trader, Pageable pageable);

    List<Trade> findByTradeCard(Card Card, Pageable pageable);

    @Query("select t from Trade t where t.trader.id != ?1 and t.tradeCard.id=?2 and t.action='sell' and t.price<=?3 and t.isCompleted=false order by t.price")
    List<Trade> findBuyTrade(Long traderId, Long cardId, Double price);

    @Query("select t from Trade t where t.trader.id != ?1 and t.tradeCard.id=?2 and t.action='buy' and t.price>=?3 and t.isCompleted=false order by t.price desc")
    List<Trade> findSellTrade(Long traderId, Long cardId, Double price);
}
