package tj.tlbljj.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import tj.tlbljj.domain.Customer;
import tj.tlbljj.domain.Pagebean;

public interface Customerservice {

	Pagebean<Customer> findbypage(Integer currentpage, Integer pagesize, DetachedCriteria criteria);

	List<Customer> findall();

	void save(Customer customer);

	Customer findbyid(String cust_id);

	void delete(Customer customer);

	void update(Customer customer);

}
