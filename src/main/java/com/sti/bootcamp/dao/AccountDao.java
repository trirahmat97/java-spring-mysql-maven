package com.sti.bootcamp.dao;

import java.util.List;

import com.sti.bootcamp.model.Account;
import com.sti.bootcamp.model.Customer;

public interface AccountDao {
	Account getById(int id) throws Exception;
	Account save(Account account) throws Exception;
	void delete(Account account) throws Exception;
	
	List<Account> getList() throws Exception;
	List<Account> getListByCustomer(Customer customer) throws Exception;
//	List<Account> getList() throws Exception;
}
