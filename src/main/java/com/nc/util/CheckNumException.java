package com.nc.util;


public class CheckNumException extends Exception{
	private static final long serialVersionUID = 1L;
	public CheckNumException(){}
	public CheckNumException(String msg){
		super(msg);
	}
	
	public void calculate (int num) throws CheckNumException{
		if (num < 0 || num > 100) {
			CheckNumException e = new CheckNumException("数组范围越界!!!");
			throw e;
		}
	}
}
