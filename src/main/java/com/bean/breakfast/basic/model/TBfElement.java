package com.bean.breakfast.basic.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * TBfElement entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_bf_element", uniqueConstraints = {})
public class TBfElement implements java.io.Serializable {

	// Fields

	private String elementId;
	private String elementName;
	private String unit;

	// Constructors

	/** default constructor */
	public TBfElement() {
	}

	/** minimal constructor */
	public TBfElement(String elementId) {
		this.elementId = elementId;
	}

	/** full constructor */
	public TBfElement(String elementId, String elementName, String unit) {
		this.elementId = elementId;
		this.elementName = elementName;
		this.unit = unit;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator = "id")
	@GenericGenerator(name = "id", strategy = "uuid")
	@Column(name = "element_id", unique = true, nullable = false, insertable = true, updatable = true, length = 32)
	public String getElementId() {
		return this.elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	@Column(name = "element_name", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getElementName() {
		return this.elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	@Column(name = "unit", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}