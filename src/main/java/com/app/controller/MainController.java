package com.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.model.Card;
import com.app.model.Trade;
import com.app.model.User;
import com.app.service.CardService;
import com.app.service.TradeService;
import com.app.service.UserService;

@Controller
public class MainController {
	private Authentication auth;
	@Autowired
	private TradeService tradeService;
	@Autowired
	private UserService userService;
	@Autowired
	private CardService cardService;

	@RequestMapping("/")
	public ModelAndView rootRedirect() {
		ModelAndView modelAndView=new ModelAndView("redirect:/CardTrading/home");
		return modelAndView;
	}


	@RequestMapping("/CardTrading/home")
	public String authHome(Model model) {
		auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("Auth home!");
		System.out.println(auth);
		System.out.println(auth.getName());
		model.addAttribute("userName", auth.getName()).addAttribute("roles", auth.getAuthorities());
		return "auth-home";
	}
	@RequestMapping("/CardTrading/trade/list")
	public ModelAndView tradeList(){
		auth = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userName", auth.getName());
        modelAndView.setViewName("trade/tradeList");
        return modelAndView;
    }
	@RequestMapping("/CardTrading/trade/myTradeList")
	public ModelAndView myTradeList(){
		auth = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userName", auth.getName());
        User user=userService.findUserByUserName(auth.getName());
        modelAndView.addObject("userId", user.getId());
        modelAndView.setViewName("trade/myTradeList");
        return modelAndView;
    }
	@RequestMapping("/CardTrading/trade/add")
	public ModelAndView tradeAdd(){
		auth = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userName", auth.getName());
        User user=userService.findUserByUserName(auth.getName());
        modelAndView.addObject("userId", user.getId());
        modelAndView.setViewName("trade/tradeAdd");
        return modelAndView;
    }
	@RequestMapping("/CardTrading/card/list")
	public ModelAndView cardList(){
		auth = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userName", auth.getName());
        modelAndView.setViewName("card/cardList");
        return modelAndView;
    }
	@RequestMapping("/CardTrading/card/tradeList")
	public ModelAndView cardTradList(@RequestParam Long cardId){
		auth = SecurityContextHolder.getContext().getAuthentication();
		Optional<Card> card=cardService.findCardByCardId(cardId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userName", auth.getName());
        modelAndView.addObject("cardName",card.get().getName());
        modelAndView.addObject("cardId",cardId);
        modelAndView.setViewName("card/tradeListOfCard");
        return modelAndView;
    }
}
