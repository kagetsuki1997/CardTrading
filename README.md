# Card Trading Center
## Requirement
- Spring boot
- jdk8
- MySQL
- Docker
## Installation
Run by docker-compose
```sh
docker-compose up
```
Run in eclipse
> Right click CardTradingApplication.java

> Run As Spring Boot App

## Document
### Activity Diagram  
![image](https://github.com/kagetsuki1997/CardTrading/blob/master/document/img/activityDiagram.PNG)
- A background service handeled by *@Scheduled* will check db every 10 sec if there are uncompleted trades can be dealed.

### Model Diagram  
![image](https://github.com/kagetsuki1997/CardTrading/blob/master/document/img/modelClassDiagram.png)

### API  
#### CardTrading
views of website
###### /CardTrading/home
- home sweet home
###### /CardTrading/trade/list
- view of top 50 trades
###### /CardTrading/trade/myTradeList
- view of my top 50 trades
###### /CardTrading/trade/add
- view of creating trade
###### /CardTrading/trade/detail
- view of trade record of a completed trade
###### /CardTrading/card/list
- view of list of cards
###### /CardTrading/card/tradeList?cardId=
- view of top 50 trades of the card
#### Card
###### GET /api/card/getAll
- return the list of cards.
#### Trade
###### POST /api/trade/add
- make a trade.
###### GET /api/trade/getAll
- return the list of top 50 trades.
###### GET /api/trade/getByTrader?traderId=
- return the list of top 50 trades created by the trader.
###### GET /api/trade/getByCard?cardId=
- return the list of top 50 trades of the card.
