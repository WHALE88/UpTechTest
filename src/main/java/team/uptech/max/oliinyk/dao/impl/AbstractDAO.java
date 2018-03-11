package team.uptech.max.oliinyk.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import team.uptech.max.oliinyk.dao.DAO;

public abstract class AbstractDAO<E> implements DAO<E> {

	private Class<E> clazz;

	public final void setClazz(Class<E> clazz) {
		this.clazz = clazz;
	}

	@PersistenceContext
	EntityManager entityManager;

	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void save(E entity) {
		getEntityManager().persist(entity);
	}

	public void delete(long id) {
		if (getEntityManager().contains(findEntityById(id))) {
			getEntityManager().remove(findEntityById(id));
		}
	}

	public void delete(E entity) {
		if (getEntityManager().contains(entity)) {
			getEntityManager().remove(entity);
		}
	}

	public E findEntityById(long id) {
		return getEntityManager().find(clazz, id);
	}

	public List<E> findAll() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<E> cq = cb.createQuery(clazz);
		Root<E> root = cq.from(clazz);
		cq.select(root);
		TypedQuery<E> query = getEntityManager().createQuery(cq);
		return query.getResultList();
	}

	public void update(E entity) {
		if (getEntityManager().contains(entity)) {
			getEntityManager().merge(entity);
		}
	}

	public List<E> getCategoriesByName(String name) {
		List<E> result = getEntityManager().createNamedQuery(clazz.getSimpleName() + ".getByName", clazz)
				.setParameter("name", name).getResultList();
		return result;
	}

	public List<E> getCategoriesByDescription(String description) {
		List<E> result = getEntityManager().createNamedQuery(clazz.getSimpleName() + ".getByDescription", clazz)
				.setParameter("description", description).getResultList();
		return result;
	}

	public List<E> getCategoriesByNameDescription(String name, String description) {
		List<E> result = getEntityManager().createNamedQuery(clazz.getSimpleName() + "getByName&Desc", clazz)
				.setParameter("name", name).setParameter("description", description).getResultList();
		return result;
	}

}
