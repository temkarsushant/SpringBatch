package com.springbatch.SpringBatch.batchconfig;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.springbatch.SpringBatch.entity.Product;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;

public class ProductItemPreparedStatementSetter implements ItemPreparedStatementSetter<Product> {

	@Override
	public void setValues(Product item, PreparedStatement ps) throws SQLException {
		ps.setInt(1, item.getProdId());
		ps.setString(2, item.getProdCode());
		ps.setDouble(3, item.getProdCost());
		ps.setDouble(4, item.getProdDisc());
		ps.setDouble(5, item.getProdGst());
	}

}
