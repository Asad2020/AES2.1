
/**
 * This is a class to link parts with suppliers
 */

package org.openxava.scm.model;

import javax.persistence.*;

import org.openxava.annotations.*;

@Entity

@Table(name = "aes_part_suppliers")

@Tab(properties= "part.name, part.number, supplier.name")
//@View

public class PartSupplier extends Identifiable{
	
	@ManyToOne (fetch=FetchType.LAZY)
	@DescriptionsList(descriptionProperties="name, number")
	@Required
	private Part part;
	public Part getPart() {
	     return part;
	}
	public void setPart(Part part) {
	     this.part = part;
	}
	
//***************************************** Link to Organization/Supplier ********************************** 
	@ManyToOne (fetch=FetchType.LAZY)
	@DescriptionsList(descriptionProperties="name")
	@Required
	private Supplier supplier;
	public Supplier getSupplier() {
	     return supplier;
	}
	public void setSupplier(Supplier supplier) {
	     this.supplier = supplier;
	}
}
