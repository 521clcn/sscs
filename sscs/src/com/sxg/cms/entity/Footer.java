package com.sxg.cms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "FOOTER_INFO")
public class Footer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2935553170629539896L;
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid",strategy="uuid")
	@Column(name = "ID")
	private String id;
	
	private String address;
	
	private String contract;
	
	private String email;
	
	private String copyright;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	

}
