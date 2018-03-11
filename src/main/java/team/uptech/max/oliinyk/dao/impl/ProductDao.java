package team.uptech.max.oliinyk.dao.impl;

import org.springframework.stereotype.Repository;

import team.uptech.max.oliinyk.dao.DAO;
import team.uptech.max.oliinyk.model.Product;

@Repository
public class ProductDao extends AbstractDAO<Product> implements DAO<Product> {
	public ProductDao() {
		super();
		setClazz(Product.class);
	}

}
