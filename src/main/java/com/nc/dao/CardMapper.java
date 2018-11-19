package com.nc.dao;

import java.util.List;
import com.nc.entity.Cards;

public interface CardMapper {
	public int inMoney(double money,int cardId);
	public int outMoney(double money,int cardId);
	public List<Cards> queryAll();
	public double queryMoney(int cardId);
}
