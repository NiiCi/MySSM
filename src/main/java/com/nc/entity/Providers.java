package com.nc.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;


@Component("providers")
public class Providers implements Serializable{
	/**
	 * ø…–Ú¡–ªØID
	 */
	private static final long serialVersionUID = 1L;
	private int providerID;
	private String provider_name;
	private String provider_add;
	private String provider_tel;
	private String account;
	private String email;
	public int getProviderID() {
		return providerID;
	}
	public void setProviderID(int providerID) {
		this.providerID = providerID;
	}
	public String getProvider_name() {
		return provider_name;
	}
	public void setProvider_name(String provider_name) {
		this.provider_name = provider_name;
	}
	public String getProvider_add() {
		return provider_add;
	}
	public void setProvider_add(String provider_add) {
		this.provider_add = provider_add;
	}
	public String getProvider_tel() {
		return provider_tel;
	}
	public void setProvider_tel(String provider_tel) {
		this.provider_tel = provider_tel;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Providers() {
		super();
	}
	public Providers(String provider_name, String provider_add, String provider_tel, String account, String email) {
		super();
		this.provider_name = provider_name;
		this.provider_add = provider_add;
		this.provider_tel = provider_tel;
		this.account = account;
		this.email = email;
	}
	public Providers(int providerID, String provider_name, String provider_add, String provider_tel, String account,
			String email) {
		super();
		this.providerID = providerID;
		this.provider_name = provider_name;
		this.provider_add = provider_add;
		this.provider_tel = provider_tel;
		this.account = account;
		this.email = email;
	}
	public Providers(int providerID) {
		super();
		this.providerID = providerID;
	}
	
	
}
