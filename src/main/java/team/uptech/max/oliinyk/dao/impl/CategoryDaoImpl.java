package team.uptech.max.oliinyk.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import team.uptech.max.oliinyk.dao.CategoryDAO;
import team.uptech.max.oliinyk.model.Category;

@Repository
public class CategoryDaoImpl extends AbstractDAO<Category> implements CategoryDAO<Category> {

	public CategoryDaoImpl() {
		super();
		setClazz(Category.class);
	}

	public List<Category> getCategoriesByName(String name) {
		List<Category> result = getEntityManager().createNamedQuery("Category.getByName", Category.class)
				.setParameter("name", name).getResultList();
		return result;
	}

	public List<Category> getCategoriesByDescription(String description) {
		List<Category> result = getEntityManager().createNamedQuery("Category.getByDescription", Category.class)
				.setParameter("description", description).getResultList();
		return result;
	}

	public List<Category> getCategoriesByNameDescription(String name, String description) {
		List<Category> result = getEntityManager().createNamedQuery("Category.getByName&Desc", Category.class)
				.setParameter("name", name).setParameter("description", description).getResultList();
		return result;
	}
}