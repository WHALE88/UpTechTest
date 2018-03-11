package team.uptech.max.oliinyk.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.uptech.max.oliinyk.dao.ProdDao;
import team.uptech.max.oliinyk.model.Product;
import team.uptech.max.oliinyk.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService<Product> {

	@Autowired
	ProdDao<Product> productDao;

	@Transactional
	public void save(Product entity) {
		productDao.save(entity);
	}

	@Transactional
	public void delete(long id) {
		productDao.delete(id);

	}

	@Transactional
	public void delete(Product entity) {
		productDao.delete(entity);
	}

	@Transactional
	public Product findEntityById(long id) {
		return productDao.findEntityById(id);
	}

	@Transactional
	public List<Product> findAll() {
		return productDao.findAll();
	}

	@Transactional
	public void update(Product entity) {
		productDao.update(entity);

	}

	@Transactional
	public List<Product> getProdByCategoryName(String name) {
		return productDao.getProdByCategoryName(name);
	}



}
