package org.openxava.scm.model;

import javax.persistence.*;

import org.openxava.annotations.*;

@Entity
@Table(name="aes_addresses")

public class Address extends Identifiable {

//********************************** Street ***********************************
	
	@Column(length=30) 
	private String street;
	
	public String getStreet() {
	return street;
	}
	public void setStreet(String street) {
	this.street = street;
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
	
}