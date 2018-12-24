package com.sti.bootcamp.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.sti.bootcamp.dao.AccountDao;
import com.sti.bootcamp.dao.repository.AccountRepository;
import com.sti.bootcamp.model.Account;
import com.sti.bootcamp.model.Customer;

public class AccountDaoImpl extends BaseImpl implements AccountDao {
	
	@Autowired
	private AccountRepository repository;
	
	@Override
	public Account getById(int id) throws Exception {
		return repository.findOne(id);
	}

	@Override
	public Account save(Account account) throws Exception {
		return repository.save(account);
	}

	@Override
	public void delete(Account account) throws Exception {
		repository.delete(account);
	}

	@Override
	public List<Account> getList() throws Exception {
		CriteriaBuilder critB = em.getCriteriaBuilder();
		CriteriaQuery<Account> query = critB.createQuery(Account.class);
		Root<Account> root = query.from(Account.class);
		
		TypedQuery<Account> q = em.createQuery(query);
		return q.getResultList();
	}

	@Override
	public List<Account> getListByCustomer(Customer customer) throws Exception {
		return repository.findByCustomer(customer);
	}

}
