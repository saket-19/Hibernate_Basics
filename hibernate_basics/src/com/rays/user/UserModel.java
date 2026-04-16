package com.rays.user;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class UserModel {
	public int add(UserDTO dto) throws Exception {
		UserDTO existDto=findByLogin(dto.getLoginId());
		if(existDto!=null) {
			throw new Exception("login id already exists");
		}
		
		SessionFactory sf=new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();

		Transaction tx = session.beginTransaction();

		session.save(dto);

		tx.commit();

		return dto.getId();

	}
	public UserDTO findByLogin(String login) {

		UserDTO dto = null;

		SessionFactory sf = new Configuration().configure().buildSessionFactory();

		Session session = sf.openSession();

		Criteria criteria = session.createCriteria(UserDTO.class);

		criteria.add(Restrictions.eq("loginId", login));

		List<UserDTO> list = criteria.list();

		if (list.size() == 1) {
			dto = new UserDTO();
			dto = list.get(0);
		}

		return dto;

	}

	public UserDTO authenticate(String login, String password) {

		UserDTO dto = null;

		SessionFactory sf = new Configuration().configure().buildSessionFactory();

		Session session = sf.openSession();

		Criteria criteria = session.createCriteria(UserDTO.class);

		criteria.add(Restrictions.eq("loginId", login));
		criteria.add(Restrictions.eq("password", password));

		List<UserDTO> list = criteria.list();

		if (list.size() == 1) {
			dto = new UserDTO();
			dto = list.get(0);
		}

		return dto;

	}
	public void update(UserDTO dto) throws Exception {

		UserDTO existDto = findByLogin(dto.getLoginId());

		if (existDto != null && existDto.getId() != dto.getId()) {
			throw new Exception("loginId already exists");
		}

		SessionFactory sf = new Configuration().configure().buildSessionFactory();

		Session session = sf.openSession();

		Transaction tx = session.beginTransaction();

		session.update(dto);

		tx.commit();

	}

	public List<UserDTO> search(UserDTO dto, int pageNo, int pageSize) {

		List<UserDTO> list = null;

		SessionFactory sf = new Configuration().configure().buildSessionFactory();

		Session session = sf.openSession();

		// select * from UserDTO where 1=1
		Criteria criteria = session.createCriteria(UserDTO.class);

		if (dto != null) {
			// and firstName like 'xyz%'
			if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
				criteria.add(Restrictions.like("firstName", dto.getFirstName() + "%"));
			}
			if (dto.getLastName() != null && dto.getLastName().length() > 0) {
				criteria.add(Restrictions.like("lastName", dto.getLastName() + "%"));
			}
			if (dto.getLoginId() != null && dto.getLoginId().length() > 0) {
				criteria.add(Restrictions.eq("loginId", dto.getLoginId()));
			}
			if (dto.getPassword() != null && dto.getPassword().length() > 0) {
				criteria.add(Restrictions.eq("password", dto.getPassword()));
			}
		}

		// limit pageNo, pageSize
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			criteria.setFirstResult(pageNo); // initial index
			criteria.setMaxResults(pageSize);// number of record
		}

		list = criteria.list();

		return list;
	}
	

}
