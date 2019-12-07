package vn.ltp.core.dao;

import java.io.Serializable;

import vn.ltp.core.domain.Member;

public interface MemberDao extends GenericDao<Serializable, Member> {

	Member logOn(String name, String password);
	long random();
}
