package team.uptech.max.oliinyk.service;

import java.util.List;

public interface GeneralService<E> {
	void save(E entity);

	void delete(long id);

	void delete(E entity);

	E findEntityById(long id);

	List<E> findAll();

	void update(E entity);
}
