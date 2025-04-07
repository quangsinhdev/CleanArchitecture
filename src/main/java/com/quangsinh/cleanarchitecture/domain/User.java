package com.quangsinh.cleanarchitecture.domain;

import java.math.BigDecimal;

public class User {
	private Long id;
	private String name;
	private String email;
	private BigDecimal balance;

	public User(Long id, String name, String email, BigDecimal balance) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.balance = balance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

}