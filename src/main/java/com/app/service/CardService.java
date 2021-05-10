package com.app.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Card;
import com.app.repository.CardRepository;

@Service
public class CardService {
    private static Logger Log = LoggerFactory.getLogger(CardService.class);

    private CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    public Card findCardByName(String cardName) {
        return cardRepository.findByName(cardName);
    }

    public Optional<Card> findCardByCardId(Long id) {
        return cardRepository.findById(id);
    }

    public Card saveCard(Card card) {
        Log.info("Save Card " + card.getId() + " " + card.getName());
        return cardRepository.save(card);
    }

}
