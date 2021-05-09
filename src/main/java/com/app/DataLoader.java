package com.app;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.app.model.Card;
import com.app.model.Trade;
import com.app.model.User;
import com.app.service.CardService;
import com.app.service.TradeService;
import com.app.service.UserService;

@Component
public class DataLoader implements ApplicationRunner {
	private static Logger Log=LoggerFactory.getLogger(DataLoader.class);
	
	@Value("${cards.default}")
	private String cardsDefaultStr;
	
	
	private CardService cardService;
	@Autowired
	private TradeService tradeService;
	@Autowired
	private UserService userService;
	
	@Autowired
	public DataLoader(CardService cardService) {
		this.cardService=cardService;
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		Log.info("**DataLoader start**");
		String[] cardNames=cardsDefaultStr.split(",");
		for(int i=0;i<cardNames.length;i++) {
			cardService.saveCard(new Card(i+1l,cardNames[i]));
		}
		
		//test create trade
//		User usr=userService.findUserByUserName("root");
//		Card card=cardService.findCardByName("Pikachu");
//		Trade trade=new Trade(usr,"sell",card,37.88,false);
//		tradeService.saveTrade(trade);
		Log.info("**DataLoader end**");
//		while(runGoRun) {
//			Thread.sleep(interval*1000);
//			Log.info("--Background MakeAllDeal");
//			tradeService.makeAllDeal();
//		}
		
		
		
	}

}
