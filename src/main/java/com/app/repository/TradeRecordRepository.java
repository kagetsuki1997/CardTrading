package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.model.Card;
import com.app.model.Trade;
import com.app.model.TradeRecord;
import com.app.model.User;

@Repository
public interface TradeRecordRepository extends JpaRepository<TradeRecord, Long> {
    @Query(value = "select * from TradeRecord where sellerId=?1 or buyerId=?1", nativeQuery = true)
    List<TradeRecord> findByTrader(Long traderId, Pageable pageable);

    List<TradeRecord> findByTradeCard(Card Card, Pageable pageable);

    Optional<TradeRecord> findById(Long id);
}
