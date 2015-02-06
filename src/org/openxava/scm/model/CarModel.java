package org.openxava.scm.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

/**
 * This class is for customer Car Models
 * 
 * @author mahmood
 *
 */

@Entity
@Table(name="aes_carmodels")

//@View(members="")
@Tab(properties="carModel")

public class CarModel extends Identifiable{

	private String carModel;
	public String getCarModel(){
		return carModel;
	}
	public void setCarModel(String carModel){
		this.carModel= carModel;
	}

//**********************************************  link to Customer Car model  *************************************
   
	@ListProperties("customer.name, secondTier")
	@OneToMany( // To declare this as a persistent collection
			mappedBy="carModel", // The member of Detail that stores the relationship
			cascade=CascadeType.ALL) // Indicates this is a collection of dependent entities
	private Collection<CustomerCarModel> customer = new ArrayList<CustomerCarModel>();
	
	public Collection<CustomerCarModel> getCustomer() {
	 return customer;
	}
	public void setCustomer(Collection<CustomerCarModel> customer) {
	 this.customer = customer;
	}

//*********************************link to Car model variance *******************************
   
	@ListProperties("carModelVariance")
	@OneToMany( 
			mappedBy="carModel",
			cascade=CascadeType.ALL)
	private Collection<CarModelVariance> carModelVariance = new ArrayList<CarModelVariance>();
	
	public Collection<CarModelVariance> getCarModelVariance() {
	 return carModelVariance;
	}
	public void setCarModelVariance(Collection<CarModelVariance> carModelVariance) {
	 this.carModelVariance = carModelVariance;
	}
	
}
