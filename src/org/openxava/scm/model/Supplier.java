package org.openxava.scm.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

@Entity
@Table(name="aes_suppliers")
@View(members= 
"name, shortName;" + 
"remarks;"
+ "Address{address};"
+ "Contacts{contact};" +
"Parts {part};" +
"Supplier Orders {supplierOrder};" +
"Supplier Quotations {quotations};"
)
@Tab(properties="name, shortName, remarks")
public class Supplier extends Identifiable{	
	
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
	@OneToMany(mappedBy="supplier", cascade=CascadeType.ALL)	
	private Collection<Address> address = new ArrayList<Address>();
	
    public Collection<Address> getAddress() {
    	return address;
    }
    public void setAddress(Collection<Address> address) {
    	this.address=address;
    } 
    
//************************** link to address ***********************************
	
  	@ListProperties("name, position, mobileNumber, phoneNumber, faxNumber, email, canCall, canEmail")
  	@OneToMany(mappedBy="supplier", cascade=CascadeType.ALL)	
  	private Collection<Contact> contact = new ArrayList<Contact>();
  	
      public Collection<Contact> getContact() {
      	return contact;
      }
      public void setContact(Collection<Contact> contact) {
      	this.contact=contact;
      } 
    
// ***************************************** Memo *************************************
	

	@Stereotype("MEMO")
	private String remarks;
	
	public String getRemarks() {
	return remarks;
	}
	public void setRemarks(String remarks) {
	this.remarks = remarks;	
	}
	
//*********************************************** link to quotation ***********************************
		
    @OneToMany(mappedBy="supplier")
    @ReadOnly

    private Collection<Quotation> quotations;

    public Collection<Quotation> getQuotations() {
        return quotations;
    }

    public void setQuotations(Collection <Quotation> quotations) {
        this.quotations = quotations;
    }
    
//*********************************************** link to Supplier Order ***********************************
	
    @ListProperties("orderNumber, orderingDate, monthYear.monthYear, paymentTerm.paymentTerm, approveAll")
    @OneToMany(mappedBy="supplier")
    @ReadOnly

    private Collection<SupplierOrder> supplierOrder;

    public Collection<SupplierOrder> getSupplierOrder() {
        return supplierOrder;
    }

    public void setSupplierOrder(Collection <SupplierOrder> supplierOrder) {
        this.supplierOrder = supplierOrder;
    }
	
//********************************** link to PartSupplier **********************************
	
    @ListProperties("part.name, part.number")
	@OneToMany( // To declare this as a persistent collection
			mappedBy="supplier", // The member of Detail that stores the relationship
			cascade=CascadeType.ALL) // Indicates this is a collection of dependent entities
	private Collection<PartSupplier> part = new ArrayList<PartSupplier>();
	
	public Collection<PartSupplier> getPart() {
	 return part;
	}
	public void setSupplier(Collection<PartSupplier> part) {
	 this.part = part;
	}
   
}
