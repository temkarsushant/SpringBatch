package com.springbatch.SpringBatch.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@Entity
@Table(name = "product")
@XStreamAlias("Product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XStreamAlias("prodId")
	private Integer prodId;
	@XStreamAlias("prodCode")
	private String prodCode;
	@XStreamAlias("prodCost")
	private Double prodCost;
	private Double prodDisc;
	private Double prodGst;

	public Product() {
		super();
	}

	public Product(Integer prodId, String prodCode, Double prodCost, Double prodDisc, Double prodGst) {
		super();
		this.prodId = prodId;
		this.prodCode = prodCode;
		this.prodCost = prodCost;
		this.prodDisc = prodDisc;
		this.prodGst = prodGst;
	}

	public Product(String prodCode, Double prodCost, Double prodDisc, Double prodGst) {
		super();
		this.prodCode = prodCode;
		this.prodCost = prodCost;
		this.prodDisc = prodDisc;
		this.prodGst = prodGst;
	}

	public Integer getProdId() {
		return prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public String getProdCode() {
		return prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}

	public Double getProdCost() {
		return prodCost;
	}

	public void setProdCost(Double prodCost) {
		this.prodCost = prodCost;
	}

	public Double getProdDisc() {
		return prodDisc;
	}

	public void setProdDisc(Double prodDisc) {
		this.prodDisc = prodDisc;
	}

	public Double getProdGst() {
		return prodGst;
	}

	public void setProdGst(Double prodGst) {
		this.prodGst = prodGst;
	}

	@Override
	public String toString() {
		return "Product [prodId=" + prodId + ", prodCode=" + prodCode + ", prodCost=" + prodCost + ", prodDisc="
				+ prodDisc + ", prodGst=" + prodGst + "]";
	}

}
