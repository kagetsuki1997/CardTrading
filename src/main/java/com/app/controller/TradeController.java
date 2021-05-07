package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.app.model.Card;
import com.app.model.Trade;
import com.app.model.User;
import com.app.service.CardService;
import com.app.service.TradeService;
import com.app.service.UserService;

@Controller
public class TradeController {
	@Autowired
	private TradeService tradeService;
	@Autowired
	private UserService userService;
	@Autowired
	private CardService cardService;
	
	@RequestMapping(value="/trade/trading",method=RequestMethod.POST)
	public String trade(Trade trade) {
		if(trade.getAction().equals("sell")||trade.getAction().equals("buy")) {
			tradeService.saveTrade(trade);
			return "success";
		}	
		else
			return "fail";
	}
	
	@RequestMapping(value="/trade/getAll",method=RequestMethod.GET)
	public List<Trade> getAllTrade(){
		return tradeService.findAll();
	}
	
	@RequestMapping(value="/trade/getByTrader/{id}",method=RequestMethod.GET)
	public List<Trade> getByTrader(@PathVariable(name="id") int id){
		User trader=userService.findUserByUserId(id);
		return tradeService.findByTrader(trader);
	}
	
	@RequestMapping(value="/trade/getByCard/{id}",method=RequestMethod.GET)
	public List<Trade> getByCard(@PathVariable(name="id") int id){
		Card card=cardService.findCardByCardId(id);
		return tradeService.findByCard(card);
	}
}
