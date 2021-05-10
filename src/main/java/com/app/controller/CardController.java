package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.model.Card;
import com.app.service.CardService;

@Controller
@RequestMapping("/api")
public class CardController {
    @Autowired
    private CardService cardService;

    @RequestMapping(value = "/card/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Card> getAllCard() {
        return cardService.findAll();
    }
}
