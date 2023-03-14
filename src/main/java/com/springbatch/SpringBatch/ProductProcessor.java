package com.springbatch.SpringBatch;

import com.springbatch.SpringBatch.entity.Product;

import org.springframework.batch.item.ItemProcessor;

public class ProductProcessor implements ItemProcessor<Product, Product> {

	@Override
	public Product process(Product item) throws Exception {

		double cost = item.getProdCost();
		System.out.println("************"+item);
		item.setProdDisc(cost * 12 / 100.0);
		item.setProdGst(cost * 22 / 100.0);
		return item;
	}

}
