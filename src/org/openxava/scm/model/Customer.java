package org.openxava.scm.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

@Entity
@Table(name="aes_customers")
@View(members= 
"name, shortName;" + 
"remarks;"
+ "Car Models{carModel};"
+ "Forecasts{customerForecast};"
+ "Address{address};"
+ "Contacts{contact};"
)
@Tab(properties="name, shortName, remarks")
public class Customer extends Identifiable {
	
//******************************************* Name ********************************
	@Column(length=50)
	@Required 
	private String name ;
	public String getName() {
	return name;
	}
	public void setName(String name) {
	this.name = name;
	}

//**************************************** Short name ****************************	
	
	@Column(length=20)
	private String shortName ;
	

	
	public String getShortName() {
	return shortName;
	}
	public void setShortName(String shortName) {
	this.shortName = shortName;
	}
	
//************************** link to address ***********************************
	
	@ListProperties("addressType.type, state, city, zipCode, street1, street2")
	@OneToMany(mappedBy="customer", cascade=CascadeType.ALL)	
	private Collection<Address> address = new ArrayList<Address>();
	
    public Collection<Address> getAddress() {
    	return address;
    }
    public void setAddress(Collection<Address> address) {
    	this.address=address;
    } 
    
//************************** link to Contact ***********************************
	
  	@ListProperties("name, position, mobileNumber, phoneNumber, faxNumber, email, canCall, canEmail")
  	@OneToMany(mappedBy="customer", cascade=CascadeType.ALL)	
  	private Collection<Contact> contact; //= new ArrayList<Contact>();
  	
      public Collection<Contact> getContact() {
      	return contact;
      }
      public void setContact(Collection<Contact> contact) {
      	this.contact=contact;
      } 
    
// ***************************************** remark *************************************
	

	@Stereotype("MEMO")
	private String remarks;
	
	public String getRemarks() {
	return remarks;
	}
	public void setRemarks(String remarks) {
	this.remarks = remarks;	
	}
	
//**********************************************  link to Customer Car model  *************************************
   
	@ListProperties("carModel.carModel, secondTier")
	@OneToMany( // To declare this as a persistent collection
			mappedBy="customer", // The member of Detail that stores the relationship
			cascade=CascadeType.ALL) // Indicates this is a collection of dependent entities
	private Collection<CustomerCarModel> carModel = new ArrayList<CustomerCarModel>();
	
	public Collection<CustomerCarModel> getCarModel() {
	 return carModel;
	}
	public void setCarModel(Collection<CustomerCarModel> carModel) {
	 this.carModel = carModel;
	}
	
//**********************************************  link to Customer Forecast  *************************************
   
	@ListProperties("monthYear.monthYear, workingDay")
	@OneToMany( // To declare this as a persistent collection
			mappedBy="customer", // The member of Detail that stores the relationship
			cascade=CascadeType.ALL) // Indicates this is a collection of dependent entities
	private Collection<CustomerForecast> customerForecast = new ArrayList<CustomerForecast>();
	
	public Collection<CustomerForecast> getCustomerForecast() {
	 return customerForecast;
	}
	public void setCustomerForecast(Collection<CustomerForecast> customerForecast) {
	 this.customerForecast = customerForecast;
	}

}
