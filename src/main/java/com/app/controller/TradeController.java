package com.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.model.Card;
import com.app.model.Trade;
import com.app.model.User;
import com.app.service.CardService;
import com.app.service.TradeService;
import com.app.service.UserService;

@Controller
@RequestMapping("/api")
public class TradeController {
    @Autowired
    private TradeService tradeService;
    @Autowired
    private UserService userService;
    @Autowired
    private CardService cardService;

    @RequestMapping(value = "/trade/add", method = RequestMethod.POST)
    @ResponseBody
    public String trade(Trade trade) {
        if (trade.getAction().equals("sell") || trade.getAction().equals("buy")) {
            tradeService.saveTrade(trade);
            return "success";
        } else
            return "fail";
    }

    @RequestMapping(value = "/trade/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Trade> getAllTrade() {
        return tradeService.findTop();
    }

    @RequestMapping(value = "/trade/getByTrader", method = RequestMethod.GET)
    @ResponseBody
    public List<Trade> getByTrader(@RequestParam Long traderId) {
        Optional<User> trader = userService.findUserByUserId(traderId);
        return tradeService.findByTrader(trader.get());
    }

    @RequestMapping(value = "/trade/getByCard", method = RequestMethod.GET)
    @ResponseBody
    public List<Trade> getByCard(@RequestParam Long cardId) {
        Optional<Card> card = cardService.findCardByCardId(cardId);
        return tradeService.findByCard(card.get());
    }
}
