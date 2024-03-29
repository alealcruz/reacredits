package org.alealcruz.creditsbuilder.model;

public class Credit implements Comparable<Credit>{
	
	private String resource;
	private int page;
	private String alt;
	private String title;
	
	public Credit(String resource, String alt, int page) {
		this.resource = resource;
		this.alt = alt;
		this.page = page;
	}
	
	public Credit(String resource, String alt, int page, String title) {
		this.resource = resource;
		this.alt = alt;
		this.page = page;
		this.title = title;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int compareTo(Credit item) {
		return Integer.toString(getPage()).compareTo(Integer.toString(item.getPage()));
		
//		if(item1.getPage()<item2.getPage())
//			return -1;
//		else if(item1.getPage()==item2.getPage())
//			return 0;
//		else
//			return 1;
	}
	
	@Override
	public String toString() {
		return "page: " + getPage() + " | resource: " + getResource() + " | alt: " + getAlt() + " | title: " + getTitle();
	}

}
