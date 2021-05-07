package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.app.model.Trade;
import com.app.service.TradeService;

@Controller
public class TradeController {
	@Autowired
	private TradeService tradeService;
	@RequestMapping(value="/trade",method=RequestMethod.POST)
	public String trade(Trade trade) {
		if(trade.getAction().equals("sell")||trade.getAction().equals("buy")) {
			tradeService.saveTrade(trade);
			return "success";
		}	
		else
			return "fail";
	}
}
