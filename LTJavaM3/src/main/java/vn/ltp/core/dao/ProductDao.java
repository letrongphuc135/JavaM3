package vn.ltp.core.dao;

import java.io.Serializable;
import java.util.List;

import vn.ltp.core.domain.Product;

public interface ProductDao extends GenericDao<Serializable, Product>{
	List<Product> getProduct(int index, int size);
	
}
