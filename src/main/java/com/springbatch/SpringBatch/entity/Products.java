package com.springbatch.SpringBatch.entity;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("products")
public class Products {
	@XStreamImplicit
	List<Product> product;
}
