package team.uptech.max.oliinyk.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.uptech.max.oliinyk.dao.DAO;
import team.uptech.max.oliinyk.model.Category;
import team.uptech.max.oliinyk.service.GeneralService;

@Service
@Transactional
public class CategoryServiceImpl implements GeneralService<Category> {

	@Autowired
	DAO<Category> categoryDao;

	@Transactional
	public void save(Category entity) {
		categoryDao.save(entity);
	}

	@Transactional
	public void delete(long id) {
		categoryDao.delete(id);

	}

	@Transactional
	public void delete(Category entity) {
		categoryDao.delete(entity);
	}

	@Transactional
	public Category findEntityById(long id) {
		return categoryDao.findEntityById(id);
	}

	@Transactional
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	@Transactional
	public void update(long id) {
		categoryDao.update(id);

	}

	@Transactional
	public void update(Category entity) {
		categoryDao.update(entity);

	}

}
