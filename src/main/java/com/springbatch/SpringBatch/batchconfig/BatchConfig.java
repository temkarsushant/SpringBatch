package com.springbatch.SpringBatch.batchconfig;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.springbatch.SpringBatch.ProductProcessor;
import com.springbatch.SpringBatch.entity.Product;
import com.springbatch.SpringBatch.listener.MyJobListener;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.xstream.XStreamMarshaller;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

//	a.reader class object.
	// Comment Code FOR Reading CSV
	/*
	 * @Bean public FlatFileItemReader<Product> reader() {
	 * FlatFileItemReader<Product> reader = new FlatFileItemReader<>();
	 * reader.setResource(new ClassPathResource("products.csv"));
	 * 
	 * reader.setLineMapper(new DefaultLineMapper<Product>() { {
	 * setLineTokenizer(new DelimitedLineTokenizer() { {
	 * setDelimiter(DELIMITER_COMMA); setNames("prodId", "prodCode", "prodCost"); }
	 * }); setFieldSetMapper(new BeanWrapperFieldSetMapper<Product>() { {
	 * setTargetType(Product.class); } }); } }); return reader; }
	 */

	// Code FOR Reading XML

	@Bean
	public StaxEventItemReader<Product> reader() {

		StaxEventItemReader<Product> reader = new StaxEventItemReader<Product>();
		reader.setResource(new ClassPathResource("products.xml"));
		reader.setFragmentRootElementName("product");

		Map<String, Class<Product>> aliseMap = new HashMap<>();
		aliseMap.put("product", Product.class);

		XStreamMarshaller marshaller = new XStreamMarshaller();
		marshaller.setAliases(aliseMap);
		marshaller.getXStream().allowTypes(new Class[] { Product.class });
		reader.setUnmarshaller(marshaller);

		return reader;
	}

//	b.processor class object.

	@Bean
	public ItemProcessor<Product, Product> processor() {
		return new ProductProcessor();
	}

	@Autowired
	private DataSource dataSource;

//	c.writer class object for CSV.
	@Bean
	public JdbcBatchItemWriter<Product> writer() {
		JdbcBatchItemWriter<Product> writer = new JdbcBatchItemWriter<>();
		writer.setDataSource(dataSource);
		writer.setSql(
				"INSERT INTO PRODUCT(prod_id,prod_code,prod_cost,prod_disc,prod_gst) VALUES(:prodId,:prodCode,:prodCost,:prodDisc,:prodGst)");
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
		return writer;
	}

//	d.listener class object
	@Bean
	public JobExecutionListener listner() {
		return new MyJobListener();
	}

//	e.autowire step builder factory
	@Autowired
	public StepBuilderFactory sf;

//	f.step object
	@Bean
	public Step stepA() {
		return sf.get("stepA").<Product, Product>chunk(3).reader(reader()).processor(processor())
				.writer(writer()).build();
	}

//	g.autowire job builder factory.
	@Autowired
	private JobBuilderFactory jf;

//	h.job object
	@Bean
	public Job jobA() {
		return jf.get("jobA").incrementer(new RunIdIncrementer()).listener(listner()).start(stepA()).build();
	}


}
