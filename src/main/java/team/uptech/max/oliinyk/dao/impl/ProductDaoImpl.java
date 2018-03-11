package team.uptech.max.oliinyk.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import team.uptech.max.oliinyk.dao.ProdDao;
import team.uptech.max.oliinyk.model.Product;

@Repository
public class ProductDaoImpl extends AbstractDAO<Product> implements ProdDao<Product> {
	public ProductDaoImpl() {
		super();
		setClazz(Product.class);
	}

	@Override
	public List<Product> getProdByCategoryName(String name) {
		List<Product> result = getEntityManager().createNamedQuery("Product.getProdByCategoryName", Product.class)
				.setParameter("name", name).getResultList();
		return result;
	}

	@Override
	public List<Product> getProdByCategoryDesc(String description) {
		List<Product> result = getEntityManager().createNamedQuery("Product.getProdByCategoryDesc", Product.class)
				.setParameter("description", description).getResultList();
		return result;
	}

}
