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
import com.sti.bootcamp.dao.TransactionDao;
import com.sti.bootcamp.model.Account;
import com.sti.bootcamp.model.Transaction;

@RestController
@RequestMapping("/Transaction")
public class TransactionController {
	
	@Autowired
	private AccountDao accountDao;

	
	@Autowired
	private TransactionDao transactionDao;
	
	@GetMapping("/transaction")
	public String viewTransaction(@RequestParam(value="id", defaultValue="") String id) {
		try {
			Transaction transaction = transactionDao.getById(Integer.valueOf(id));
			if(transaction == null) {
				return "data tidak ditmukan";
			}else {
				return "hello "+ transaction.getAmount();
			}
		}catch(NumberFormatException e) {
			return "salah format input";
		}catch(Exception e) {
			return String.format("terjadi kesalahan : %s", e.getMessage());
		}
	}
	
	@PostMapping("/transaction")
	public Transaction post(@RequestBody Transaction transaction) throws Exception{
		Transaction data = transactionDao.save(transaction);
		return data;
	}
	
	@DeleteMapping("/transaction/{id}")
	public void delet(@PathVariable ("id") Transaction data) throws Exception{
		transactionDao.delete(data);
	}
	
	@PutMapping("/transaction")
	public Transaction update(@RequestBody Transaction transaction) throws Exception {
		Transaction update = transactionDao.save(transaction);
		return update;
	}
	
	@GetMapping("/transactions")
	public List<Transaction> getlist() throws Exception{
		List<Transaction> list = transactionDao.getList();
		return list;
	}
	
	@GetMapping(value="/list")
	public List<Transaction> getList(@RequestParam(name="account", defaultValue="") String id) throws NumberFormatException, Exception{
		if(!StringUtils.isEmpty(id)) {
			Account checkAccount = accountDao.getById(Integer.parseInt(id));
			if(checkAccount==null) {
				throw new Exception("account tidak ditemukan");
			}
			return transactionDao.getListByAccount(checkAccount);
		}else {
			return transactionDao.getList();
		}
	}

	
}
