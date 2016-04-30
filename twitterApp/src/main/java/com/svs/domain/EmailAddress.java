package com.svs.domain;

public class EmailAddress {

	private String domain;
	private String localPart;
	private final String address;
	

	
	public EmailAddress(String domain, String localPart){
		this.setDomain(domain);
		this.setLocalPart(localPart);
		this.address = localPart + "@" + domain;
	}

	public EmailAddress() {
		this.address = "test@twitterApp.com";
	}

	public String getLocalPart() {
		return localPart;
	}

	public void setLocalPart(String localPart) {
		this.localPart = localPart;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getAddress() {
		return  localPart + "@" + domain;
	}
	
	public String toString(){
		return this.getAddress();
	}
	
	
}
