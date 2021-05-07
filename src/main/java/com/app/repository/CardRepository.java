package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    Card findByName(String cardName);
    Card findById(int id);

}
