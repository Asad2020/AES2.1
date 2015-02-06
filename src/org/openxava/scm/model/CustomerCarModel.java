package org.openxava.scm.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

@Entity
@Table(name = "aes_cusomer_carmodels")

@Tab(properties = "customer.name, carModel.carModel, secondTier")

public class CustomerCarModel extends Identifiable{

//******************************* Link to Customer ************************** 
	
	@ManyToOne (fetch=FetchType.LAZY)
	@DescriptionsList(descriptionProperties="name, shortName")
	@Required
	private Customer customer;
	public Customer getCustomer() {
	     return customer;
	}
	public void setCustomer(Customer customer) {
	     this.customer = customer;
	}
		
//***************************************** Link to Car Model ********************************** 
	
	@ManyToOne (fetch=FetchType.LAZY)
	@DescriptionsList(descriptionProperties="carModel")
	@Required
	private CarModel carModel;
	
	public CarModel getCarModel() {
	     return carModel;
	}
	public void setCarModel(CarModel carModel) {
	     this.carModel = carModel;
	}	
	
//********************************** Second tier ***********************************	

	private boolean secondTier;
	public boolean getSecondTier() {
	return secondTier;
	}
	public void setSecondTier(boolean secondTier) {
	this.secondTier = secondTier;
	}
	


}
