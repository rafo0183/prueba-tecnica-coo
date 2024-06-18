package cl.coopeuch.pruebatecnica.dao;

import java.util.List;

public interface DAO<T, K> {
	T insert(T t);
	
	T update(T t);
	
	void delete(T t);
	
	List<T> getAll();
	
	T get(K id);
}
