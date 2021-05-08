package com.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tradeRecord")
public class TradeRecord {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
	
	@ManyToOne
	@JoinColumn(name = "sellerId")
	private User seller;
	
	@ManyToOne
	@JoinColumn(name = "buyerId")
	private User buyer;
	
	@ManyToOne
	@JoinColumn(name = "cardId")
	private Card tradeCard;
	
	@Column(name= "soldPrice")
	private Double soldPrice;
	
	@ManyToOne
	@JoinColumn(name = "sellTradeId")
	private Trade sellTrade;
	
	@ManyToOne
	@JoinColumn(name = "buyTradeId")
	private Trade buyTrade;
	
	@CreationTimestamp
	@Column(name = "completeTime")
	private Date completeTime;

}
