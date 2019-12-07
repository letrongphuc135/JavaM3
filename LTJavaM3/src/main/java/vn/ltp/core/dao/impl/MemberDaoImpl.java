package vn.ltp.core.dao.impl;

import java.io.Serializable;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import vn.ltp.core.common.util.HibernateUtil;
import vn.ltp.core.dao.MemberDao;
import vn.ltp.core.domain.Member;

public class MemberDaoImpl extends AbstractDao<Serializable, Member> implements MemberDao {

	public Member logOn(String username, String password) {
		Member member = new Member();
		Transaction transaction = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			transaction = session.beginTransaction();
			StringBuilder sql = new StringBuilder("from ");
			sql.append(this.getPersistenceClassName());
			sql.append(" where username = :value1");
			sql.append(" and password := value2");
			Query query = session.createQuery(sql.toString());
			;
			query.setParameter("value1", username);
			query.setParameter("value2", password);
			member = (Member) query.getResultList().get(0);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return member;
	}

	public long random() {
		Random rand = new Random();
		long a = rand.nextInt();
		long b = rand.nextInt();
		return a * b;
	}

}
