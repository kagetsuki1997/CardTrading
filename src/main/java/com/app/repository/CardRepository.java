package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    Card findByName(String cardName);
    Optional<Card> findById(Long id);
}
