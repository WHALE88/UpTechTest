package team.uptech.max.oliinyk.dao.impl;

import org.springframework.stereotype.Repository;

import team.uptech.max.oliinyk.dao.DAO;
import team.uptech.max.oliinyk.model.Category;

@Repository
public class CategoryDAO extends AbstractDAO<Category> implements DAO<Category>{

	public CategoryDAO() {
		super();
		setClazz(Category.class);
	}

}