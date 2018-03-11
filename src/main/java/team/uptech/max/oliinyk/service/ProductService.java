package team.uptech.max.oliinyk.service;

import java.util.List;

import team.uptech.max.oliinyk.model.Product;

public interface ProductService<E> {
	void save(E entity);

	void delete(long id);

	void delete(E entity);

	E findEntityById(long id);

	List<E> findAll();

	void update(E entity);

	List<Product> getProdByCategoryName(String name);
	
	List<Product> getProdByCategoryDesc(String description);
}
