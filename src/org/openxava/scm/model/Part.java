/**
 * Class: Part contains all the properties for he part in the BOM
 * In addition, it has the links to the other classes
 * Wrote by: Mahmood Asadi
 */

package org.openxava.scm.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

@Entity
@Table(name="aes_parts")


@View(members=
	"General Inforation [" +
	" name;" +
	" number;" +
	" backNumber;" +
	" category;" +
	" uom;" + 
	" standardPacking;" +
	" bufferStock;" +
	"]" +
	"Photo [" +
	" photo;" + 
	"]" +
	"More Photo [" +
	" morePhoto;" + 
	"]" +
	"Material Information {" +
	" materialType;" +
	" specification;" +
	" cutSizeThickness;" +
	" cutSizeWidth;" +
	" cutSizePitch;" +
	" cutSizeLength;" +
	" cutSize;" +	
	"stripLength;" +
	" partPerStrip;" +
	" blankWeight;" +
	"}" + 
	"Car Models {" +
	" carModelVariance;" +
	"}" + 
	//"Car Models {" +
	//" allCarModel;" +
	//"}" + 
	"Purchasing Information {" +
	" purchaseType;" +
	" Suppliers {" +
	" supplier;" +
	"}" +
	"Quotation Information {" +
	" quotationDetail;" +
	"}" +
	"Purchasing Information {" +
	" supplierOrderDetail;" +
	"}" +
	"}" + 
	" BOM Information {" +
	" parent;" +
	" child;" +
	"}" )

@Tab( properties="name, number, backNumber,category.name, uom.uom, purchaseType.type, photo")

public class Part extends Identifiable{	
	
//************************************** name *******************************************	
	
	@Column(name = "part_name" ,length=30)
	@Required 
	private String name;
	
	public String getName() {
	return name;
	}
	public void setName(String name) {
	this.name = name;
	}

//************************************ number *******************************************	
	
	@Column(name = "part_number" ,length=30)
	private String number;
	
	public String getNumber() {
	return number;
	}
	public void setNumber(String number) {
	this.number = number;
	}

//************************************ backNumber **************************************	
	
	@Column(name = "back_number" ,length=30)
	private String backNumber;
	
	public String getBackNumber() {
	return backNumber;
	}
	public void setBackNumber(String backNumber) {
	this.backNumber = backNumber;
	}

//************************************* photo ******************************************	
	
	@Stereotype("PHOTO") 
	private byte [] photo;	
    public byte[] getPhoto() {
        return photo;
    }
    public void setPhoto(byte[] photo) {
        this.photo=photo;
    }

//************************************ morePhoto ***********************************    
    
	@Stereotype("IMAGES_GALLERY") 
	@Column(length=90)
	private String morePhoto;	
    public String getMorePhoto() {
        return morePhoto;
    }
    public void setMorePhoto(String morePhoto) {
        this.morePhoto=morePhoto;
    }

//********************************** specification *********************************    
    
	@ManyToOne (fetch=FetchType.LAZY)
	@DescriptionsList
	private PartSpecification specification;
	
	public PartSpecification getSpecification() {
	     return specification;
	}
	public void setSpecification(PartSpecification specification) {
	     this.specification = specification;
	}

//********************************* category ***************************************	
	
	@ManyToOne (fetch=FetchType.LAZY)
	@DescriptionsList
	private PartCategory category;
	
	public PartCategory getCategory() {
	     return category;
	}
	public void setCategory(PartCategory category) {
	     this.category = category;
	}

//*********************************** materialType ***********************************	
	
	@ManyToOne (fetch=FetchType.LAZY)
	@DescriptionsList(descriptionProperties="type")
	private MaterialType materialType;
	
	public MaterialType getMaterialType() {
	     return materialType;
	}
	public void setMaterialType(MaterialType materialType) {
	     this.materialType = materialType;
	}
	
//****************************  link to Uom *******************************************

	@ManyToOne (fetch=FetchType.LAZY)
	@DescriptionsList(descriptionProperties="uom")
	private Uom uom;  

	public Uom getUom() {
	     return uom;
	}
	public void setUom(Uom uom) {
	     this.uom = uom;
	}

//*********************************** blankWeight***********************************
	
	@Depends("cutSizeThickness, cutSizeWidth, cutSizePitch, cutSizeLength, partPerStrip, stripLength")
	@Transient
	@Column(length=10, precision = 8, scale = 4)
	@DisplaySize(10) 
	public double getBlankWeight(){
		double result = 0;
		if (materialType != null && materialType.getType().toLowerCase().equals("coil")){
			result = (cutSizeThickness * cutSizeWidth * cutSizePitch) * 0.00000785;	
		} else if (materialType != null && materialType.getType().toLowerCase().equals("pre-cut")){
			result = (cutSizeThickness * cutSizeWidth * cutSizeLength) * 0.00000785;		
		} else if (materialType != null && materialType.getType().toLowerCase().equals("strip")){
			result = (cutSizeThickness * cutSizeWidth * stripLength) * 0.00000785 / partPerStrip;
		}
		return result;			
	}
	
//*********************************** Cut size ***********************************

	@Depends("cutSizeThickness, cutSizeWidth, cutSizePitch, cutSizeLength, stripLength")
	@Transient
	public String getCutSize(){
		String result = null;
		if (materialType != null && materialType.getType().toLowerCase().equals("coil")){
			result = Double.toString(cutSizeThickness) + " X " + Double.toString(cutSizeWidth) + " X Coil";	
		} else if (materialType != null && materialType.getType().toLowerCase().equals("pre-cut")){
			result = Double.toString(cutSizeThickness) + " X " + Double.toString(cutSizeWidth) + " X " + Double.toString(cutSizeLength);			
		} else if (materialType != null && materialType.getType().toLowerCase().equals("strip")){
			result = Double.toString(cutSizeThickness) + " X " + Double.toString(cutSizeWidth) + " X " + Double.toString(stripLength);
		}
		return result;			
	}
		
//************************************	cutSizeThickness ***************************

	@Column(name = "cut_size_thicknesst" ,length=5)
	private double cutSizeThickness;
	
	public double getCutSizeThickness() {
	return cutSizeThickness;
	}
	public void setCutSizeThickness(double cutSizeThickness) {
	this.cutSizeThickness = cutSizeThickness;
	}

//************************************	cutSizeWidth ******************************	
	
	@Column(name = "cut_size_width" ,length=5)
	private double cutSizeWidth;
	
	public double getCutSizeWidth() {
	return cutSizeWidth;
	}
	public void setCutSizeWidth(double cutSizeWidth) {
	this.cutSizeWidth = cutSizeWidth;
	}

//************************************	cutSizePitch ******************************		
	
	@Column(name = "cut_size_pitch" ,length=5)
	private double cutSizePitch;
	
	public double getCutSizePitch() {
	return cutSizePitch;
	}
	public void setCutSizePitch(double cutSizePitch) {
	this.cutSizePitch = cutSizePitch;
	}

//************************************	cutSizeLength ******************************		
	
	@Column(name = "cut_size_length" ,length=5)
	private double cutSizeLength;
	
	public double getCutSizeLength() {
	return cutSizeLength;
	}
	public void setCutSizeLength(double cutSizeLength) {
	this.cutSizeLength = cutSizeLength;
	}

//************************************	stripLength ******************************		

	@Column(name = "strip_length" ,length=5)
	private double stripLength;
	
	public double getStripLength() {
	return stripLength;
	}
	public void setStripLength(double stripLength) {
	this.stripLength = stripLength;
	}	
	
//************************************	partPerStrip ******************************	
	
	@Column(name = "part_per_strip" ,length=5)
	private int partPerStrip;
	
	public int getPartPerStrip() {
	return partPerStrip;
	}
	public void setPartPerStrip(int partPerStrip) {
	this.partPerStrip = partPerStrip;
	}



//************************************	standardPacking ******************************		
	
	@Column(name = "standard_packing" ,length=5)
	private int standardPacking;
	
	public int getStandardPacking() {
	return standardPacking;
	}
	public void setStandardPacking(int standardPacking) {
	this.standardPacking = standardPacking;
	}

//************************************	bufferStock *********************************		
	
	@Column(name = "buffer_stock" ,length=5)
	private int bufferStock;
	
	public int getBufferStock() {
	return bufferStock;
	}
	public void setBufferStock(int bufferStock) {
	this.bufferStock = bufferStock;
	}

//************************************	purchaseType *********************************		
	
	@ManyToOne (fetch=FetchType.LAZY)
	@DescriptionsList(descriptionProperties="type")
	private PurchaseType purchaseType;
	
	public PurchaseType getPurchaseType() {
	     return purchaseType;
	}
	public void setPurchaseType(PurchaseType purcahseType) {
	     this.purchaseType = purcahseType;
	}

//********************************** link to PartSupplier **********************************
	
    @ListProperties("supplier.name")
	@OneToMany( // To declare this as a persistent collection
			mappedBy="part", // The member of Detail that stores the relationship
			cascade=CascadeType.ALL) // Indicates this is a collection of dependent entities
	private Collection<PartSupplier> supplier = new ArrayList<PartSupplier>();
	
	public Collection<PartSupplier> getSupplier() {
	 return supplier;
	}
	public void setSupplier(Collection<PartSupplier> supplier) {
	 this.supplier = supplier;
	}
		
//****************************  link to Part Car model variance ***********************
   
	@ListProperties("carModelVariance.carModel.carModel,carModelVariance.carModelVariance, quantityUsed")
	@OneToMany( // To declare this as a persistent collection
			mappedBy="part", // The member of Detail that stores the relationship
			cascade=CascadeType.ALL) // Indicates this is a collection of dependent entities
	private Collection<PartCarModelVariance> carModelVariance = new ArrayList<PartCarModelVariance>();
	
	public Collection<PartCarModelVariance> getCarModelVariance() {
	 return carModelVariance;
	}
	public void setCarModelVariance(Collection<PartCarModelVariance> carModelVariance) {
	 this.carModelVariance = carModelVariance;
	}
	
//*********************************** All Car model ***********************************

/*	@Depends("carModelVariance")
	@Transient
	public String getAllCarModel(){
		String result = "need to be codded";
		if (carModelVariance.size() !=0){
			result = carModelVariance.iterator().next().getCarModelVariance().getCarModel().getCarModel();
		}
		return result;
	}*/	
		
//*******************************  link to Supplier Order Detail ********************************
	  
	@ListProperties("parent.supplier.name, parent.orderNumber, parent.monthYear.monthYear,  orderQuantity, part.uom.uom")
	@ReadOnly
	@OneToMany( // To declare this as a persistent collection
			mappedBy="part", // The member of Detail that stores the relationship
			cascade=CascadeType.REMOVE) // Indicates this is a collection of dependent entities
	private Collection<SupplierOrderDetail> supplierOrderDetail = new ArrayList<SupplierOrderDetail>();
	
	public Collection<SupplierOrderDetail> getSupplierOrderDetail() {
	 return supplierOrderDetail;
	}
	public void setSupplierOrderDetail(Collection<SupplierOrderDetail> supplierOrderDetail) {
	 this.supplierOrderDetail = supplierOrderDetail;
	}	
		
//*****************************  link to Part Request Detail *************************************
  
	@ListProperties("partRequest.fromLocation, partRequest.toLocation, quantityRequest")
	@OneToMany( // To declare this as a persistent collection
			mappedBy="part", // The member of Detail that stores the relationship
			cascade=CascadeType.REMOVE) // Indicates this is a collection of dependent entities
	private Collection<PartRequestDetail> partRequestDetail = new ArrayList<PartRequestDetail>();
	
	public Collection<PartRequestDetail> getPartRequestDetail() {
	 return partRequestDetail;
	}
	public void setPartRequestDetail(Collection<PartRequestDetail> partRequestDetail) {
	 this.partRequestDetail = partRequestDetail;
	}

//********************************  link to Part Child/Parent *****************************
  
	@ListProperties("child.name, child.number, quantityUsed")
	@OneToMany( // To declare this as a persistent collection
			mappedBy="parent", // The member of Detail that stores the relationship
			cascade=CascadeType.REMOVE) // Indicates this is a collection of dependent entities
	private Collection<PartChild> child = new ArrayList<PartChild>();
	
	public Collection<PartChild> getChild() {
	 return child;
	}
	public void setChild(Collection<PartChild> child) {
	 this.child = child;
	}		
	
//****************************  link to Part Child/Child ***********************************

	@ListProperties("parent.name, parent.number, quantityUsed")
	@OneToMany( // To declare this as a persistent collection
			mappedBy="child", // The member of Detail that stores the relationship
			cascade=CascadeType.REMOVE) // Indicates this is a collection of dependent entities
	private Collection<PartChild> parent = new ArrayList<PartChild>();
	
	public Collection<PartChild> getParent() {
	 return parent;
	}
	public void setParent(Collection<PartChild> parent) {
	 this.parent = parent;
	}
	
//****************************  link to partTransaction ***********************
	@NoCreate
	@ReadOnly 
	@ListProperties("")
	@OneToMany( // To declare this as a persistent collection
			mappedBy="part", // The member of Detail that stores the relationship
			cascade=CascadeType.ALL) // Indicates this is a collection of dependent entities
	private Collection<PartTransaction> partTransaction = new ArrayList<PartTransaction>();
	
	public Collection<PartTransaction> getPartTransaction() {
	 return partTransaction;
	}
	public void setPartTransaction(Collection<PartTransaction> partTransaction) {
	 this.partTransaction = partTransaction;
	}	
	// test

//**********************************  link to Quotation Detail **********************************
  
	@ListProperties("parent.supplier.name, parent.quotationNumber, price, parent.currency.currency, parent.validFrom, parent.validUntil")
	@ReadOnly
	@OneToMany( // To declare this as a persistent collection
			mappedBy="part", // The member of Detail that stores the relationship
			cascade=CascadeType.ALL) // Indicates this is a collection of dependent entities
	private Collection<QuotationDetail> quotationDetail = new ArrayList<QuotationDetail>();
	
	public Collection<QuotationDetail> getQuotationDetail() {
	 return quotationDetail;
	}
	public void setQuotationDetail(Collection<QuotationDetail> quotationDetail) {
	 this.quotationDetail = quotationDetail;
	}
}


