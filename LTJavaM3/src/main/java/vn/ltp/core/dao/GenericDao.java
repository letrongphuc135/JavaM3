package vn.ltp.core.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao <ID extends Serializable, T>{
	List<T> findAll();
	int save(T entity);
	T findById(ID id);
	T update(T entity);
	Integer delete(List<Integer> ids);
	int count();
	List<T> search(String properties, Object value, int index, int size);
	
}
