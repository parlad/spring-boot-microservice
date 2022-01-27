package com.movie.catalog.models;

public class CatalogItem {

	private String name;
	private String desc;
	private int rate;

	public CatalogItem() {

	}

	public CatalogItem(String name, String desc, int rate) {
		super();
		this.name = name;
		this.desc = desc;
		this.rate = rate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "CatalogItem [name=" + name + ", desc=" + desc + ", rate=" + rate + "]";
	}

}
