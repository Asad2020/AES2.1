package org.openxava.scm.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.jpa.*;

@Entity
@Table(name="aes_quotation_details")

//@Tab(properties=" partSupplier.part.cutSize")
@Tab(properties="parent.quotationNumber, parent.supplier.name, part.name, part.number, part.uom.uom, price, parent.currency.currency")

@View(members=
"part;" +
"price;" +
"parent")


public class QuotationDetail extends Identifiable {
	
//**************************************************** link to part **************************************

	@ManyToOne (fetch=FetchType.LAZY)
	@DescriptionsList(descriptionProperties="name, number")
	private Part part;
	public Part getPart() {	
	      return part;
	}
	public void setPart(Part part) {
	     this.part = part;
	}
	
	/* Query query = XPersistence.getManager().createQuery(
	 "from PartSupplier ps where " +
	 "ps.supplier.oid = :parent.supplier.oid ");
	 return query.getResultList();*/
	
//*************************************************** Price ***************************	
	@Column(length=10, precision = 8, scale = 4)	
	private double price;
	
	public double getPrice() {
	return price;
	}
	public void setPrice(double price) {
	this.price = price;
	}
	
//***************************************** Link to Quotation ********************************** 
	
	@ManyToOne (fetch=FetchType.LAZY)
	@DescriptionsList(descriptionProperties="quotationNumber")
	@Required
	private Quotation parent;
	public Quotation getParent() {
	     return parent;
	}
	public void setParent(Quotation parent) {
	     this.parent = parent;
	}

}
