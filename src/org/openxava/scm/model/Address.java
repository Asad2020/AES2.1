package org.openxava.scm.model;

import javax.persistence.*;

import org.openxava.annotations.*;

@Entity
@Table(name="aes_addresses")

public class Address extends Identifiable {
	
//******************************* Link to Address type *************************************	

	@ManyToOne (fetch=FetchType.LAZY)
	@DescriptionsList(descriptionProperties="type")
	private AddressType addressType;
	
	public AddressType getAddressType() {
	     return addressType;
	}
	public void setAddressType(AddressType addressType) {
	     this.addressType = addressType;
	}

//********************************** Street1 ***********************************
	
	@Column(length=65) 
	private String street1;
	
	public String getStreet1() {
	return street1;
	}
	public void setStreet1(String street1) {
	this.street1 = street1;
	}	
	
//********************************** Street2 ***********************************

	@Column(length=65) 
	private String street2;
	
	public String getStreet2() {
	return street1;
	}
	public void setStreet2(String street2) {
	this.street2 = street2;
	}

//******************************** Zip Code ************************************	
	
	@Column(name="zip_code", length=16)
	private int zipCode;
	
	public int getZipCode() {
	return zipCode;
	}
	public void setZipCode(int zipCode) {
	this.zipCode = zipCode;
	}
	
//******************************** City ****************************************	
	
	@Column(length=20)
	private String city;
	
	public String getCity() {
	return city;
	}
	public void setCity(String city) {
	this.city = city;
	}
	
//******************************** State ***************************************	
	
	@Column(length=30)
	private String state;
	
	public String getState() {
	return state;
	}
	public void setState(String state) {
	this.state = state;
	}

//************************************ link to Supplier ********************************	

	@ManyToOne (fetch=FetchType.LAZY)
	@DescriptionsList(descriptionProperties="name")
	private Supplier supplier;
	public Supplier getSupplier() {
	     return supplier;
	}
	public void setSupplier(Supplier supplier) {
	     this.supplier = supplier;
	}
	
//************************************ link to Customer ********************************	

	@ManyToOne (fetch=FetchType.LAZY)
	@DescriptionsList(descriptionProperties="name")
	private Customer customer;
	public Customer getCustomer() {
	     return customer;
	}
	public void setCustomer(Customer customer) {
	     this.customer = customer;
	}	


	
}
