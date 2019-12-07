package vn.ltp.core.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import vn.ltp.core.common.util.HibernateUtil;
import vn.ltp.core.dao.ProductDao;
import vn.ltp.core.domain.Product;

public class ProductDaoImpl extends AbstractDao<Serializable, Product> implements ProductDao{

	public List<Product> getProduct(int index, int size) {
		List<Product> list = new ArrayList<Product>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			StringBuilder sql = new StringBuilder("from ");
			sql.append(getPersistenceClassName());
			Query query = session.createQuery(sql.toString());
			query.setFirstResult((index-1) * size);
			query.setMaxResults(size);
			list = query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	

	
	
}
