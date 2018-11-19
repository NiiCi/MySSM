package com.nc.service;

import java.util.IllformedLocaleException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nc.dao.CardMapper;
import com.nc.entity.Cards;



@Service
public class CardService {
	@Autowired
	CardMapper cardMapper;
	public int inMoney(double money,int cardID){
		return cardMapper.inMoney(money,cardID);
	}
	public int outMoney(double money,int cardID){
		return cardMapper.outMoney(money,cardID);
	}
	public List<Cards> queryAll(){
		return cardMapper.queryAll();
	}
	public double queryMoney(int cardId){
		return cardMapper.queryMoney(cardId);
	}
	@Transactional
	public void ioMoney(double money,int fromId,int toId){
		double m = cardMapper.queryMoney(fromId);
		System.out.println("”‡∂Ó-----: "+m);
		cardMapper.outMoney(money, fromId);
		if (m<money) {
			throw new IllformedLocaleException("”‡∂Ó≤ª◊„!!!");
		}
		cardMapper.inMoney(money, toId);
	}
}
