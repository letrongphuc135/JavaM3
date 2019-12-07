package vn.ltp.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import vn.ltp.core.common.util.HibernateUtil;
import vn.ltp.core.dao.GenericDao;

public class AbstractDao <ID extends Serializable, T> implements GenericDao<Serializable, T> {
	
	private Class<T> persistenceClass;
	
	public AbstractDao() {
		this.persistenceClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}
	
	public String getPersistenceClassName() {
		return persistenceClass.getSimpleName();
	}

	public List findAll() {	
		List<T> list = new ArrayList<T>();
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            StringBuilder sql = new StringBuilder("from ");
            sql.append(this.getPersistenceClassName());
            list = session.createQuery(sql.toString()).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
        return list;
	}

	public int save(T entity) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(entity);
			transaction.commit();
			return 1;
		} catch (Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}finally {
			session.close();
		}
		return 0;
	}

	public T findById(Serializable id) {
		T result = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			result = (T) session.get(persistenceClass, id);
			if(result == null) {
				throw new ObjectNotFoundException("NOT FOUND" + id, null);
			}
			transaction.commit();
		} catch (Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}finally {
			session.close();
		}
		return result;
	}

	public T update(T entity) {
		T result = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Object object = session.merge(entity);
			result = (T) object;
			transaction.commit();
		} catch (Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}finally {
			session.close();
		}
		return result;
	}

	public Integer delete(List<Integer> ids) {
		int count = 0;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			for(Integer item : ids) {
				T t = session.get(persistenceClass, item);
                session.delete(t);
                count++;
			}
			transaction.commit();
		} catch (Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}finally {
			session.close();
		}
		return count;
	}

	public int count() {
		int count = 0;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			StringBuilder sql = new StringBuilder("from ");
			sql.append(getPersistenceClassName());
			count = session.createQuery(sql.toString()).getResultList().size();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return count;
	}

	public List<T> search(String properties, Object value, int index, int size) {
		List<T> list = new ArrayList<T>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			StringBuilder sql = new StringBuilder("from ");
			sql.append(getPersistenceClassName());
			if (properties != null && value != null){
//                sql.append(" where ").append(properties).append(" like = :value");
                sql.append(" where ").append(properties).append(" like ").append('\'').append("%" + value + "%").append('\'');
            }
			Query query = session.createQuery(sql.toString());
			query.setFirstResult((index - 1) * size);
			query.setMaxResults(size);
//			if(value != null){
//                query.setParameter("value", "%" + value + "%");
//            }
			list = query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	

	

	

	
	

}
