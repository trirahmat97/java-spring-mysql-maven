package com.bootcamp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sti.bootcamp.dao.AccountDao;
import com.sti.bootcamp.dao.CustomerDao;
import com.sti.bootcamp.model.Account;
import com.sti.bootcamp.model.Customer;


@RestController
@RequestMapping("/Account")
public class AccountController {
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private AccountDao accountDao;
	
	@GetMapping("/account")
	public String viewAccount(@RequestParam(value="id", defaultValue="") String id) {
		try {
			Account account = accountDao.getById(Integer.valueOf(id));
			if(account == null) {
				return "data tidak ditmukan";
			}else {
				return "hello "+ account.getBalance();
			}
		}catch(NumberFormatException e) {
			return "salah format input";
		}catch(Exception e) {
			return String.format("terjadi kesalahan : %s", e.getMessage());
		}
	}
	
	@PostMapping("/account")
	public Account postCustomer(@RequestBody Account account) throws Exception{
		Account data = accountDao.save(account);
		return data;
	}
	
	@DeleteMapping("/account/{id}")
	public void customer(@PathVariable ("id") Account data) throws Exception{
		accountDao.delete(data);
	}
	
	@PutMapping("/account")
	public Account update(@RequestBody Account account) throws Exception {
		Account update = accountDao.save(account);
		return update;
	}
	
	@GetMapping("/accounts")
	public List<Account> getlist() throws Exception{
		List<Account> list = accountDao.getList();
		return list;
	}
	
	@GetMapping(value="/list")
	public List<Account> getList(@RequestParam(name="customer", defaultValue="") String customer) throws NumberFormatException, Exception{
		if(!StringUtils.isEmpty(customer)) {
			Customer checkCustomer = customerDao.getById(Integer.parseInt(customer));
			if(checkCustomer==null) {
				throw new Exception("customer tidak ditemukan");
			}
			return accountDao.getListByCustomer(checkCustomer);
		}else {
			return accountDao.getList();
		}
	}

}
