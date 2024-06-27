package com.alti.baseTemplate.entity;

import org.springframework.data.annotation.Id;

public class MerchantStore {

	@Id
	private Integer id;

	private String name;

	private String merchantType;

	public MerchantStore() {

	}

	public MerchantStore(String name, String merchantType) {
		super();
		// this.id = id;
		this.name = name;
		this.merchantType = merchantType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}

}
