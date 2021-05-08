package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.model.Trade;
import com.app.model.User;
import com.app.service.TradeService;
import com.app.service.UserService;

@Controller
public class MainController {
	private Authentication auth;
	@Autowired
	private TradeService tradeService;
	@Autowired
	private UserService userService;

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
}