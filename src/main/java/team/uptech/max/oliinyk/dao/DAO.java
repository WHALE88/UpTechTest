package team.uptech.max.oliinyk.dao;

import java.util.List;

public interface DAO<E> {
	void save(E entity);

	void delete(long id);

	void delete(E entity);

	E findEntityById(long id);

	List<E> findAll();

	void update(long id);

	void update(E entity);
}