package com.app.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "trade")
public class Trade {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	
	@Version
	private Long version=null;
	
	@ManyToOne
	@JoinColumn(name = "traderId")
	private User trader;
	
	@Column(name = "action")
	private String action;
	
	@ManyToOne
	@JoinColumn(name = "cardId")
	private Card tradeCard;
	
	@Column(name = "price")
	private Double price;
	
	@CreationTimestamp
	@Column(name = "createTime")
	private Date createTime;
	
	
	@Column(name ="completeTime")
	private Date completeTime;
	
	@Column(name = "isCompleted")
	private boolean isCompleted;
	
	@ManyToOne
	@JoinColumn(name="recordId")
	@JsonIgnore
	private TradeRecord tradeRecord; 
	
	public Trade(User trader,String action,Card tradeCard,Double price,boolean isCompleted) {
		this.trader=trader;
		this.action=action;
		this.tradeCard=tradeCard;
		this.price=price;
		this.isCompleted=isCompleted;
	}
}
