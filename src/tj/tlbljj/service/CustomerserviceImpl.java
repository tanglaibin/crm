package tj.tlbljj.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import tj.tlbljj.dao.CustomerDao;
import tj.tlbljj.domain.Customer;
import tj.tlbljj.domain.Pagebean;

@Transactional
public class CustomerserviceImpl implements Customerservice {
private CustomerDao cusdao;
public void setCusdao(CustomerDao cusdao) {
	this.cusdao = cusdao;
}
public Pagebean<Customer> findbypage(Integer currentpage, Integer pagesize, DetachedCriteria criteria) {
	return cusdao.findbypage(currentpage,pagesize,criteria);
}
@Override
public List<Customer> findall() {
	// TODO Auto-generated method stub
	return cusdao.findall();
}
@Override
public void save(Customer customer) {
	cusdao.save(customer);
}
@Override
public Customer findbyid(String cust_id) {
	// TODO Auto-generated method stub
	return cusdao.findbyid(cust_id);
}
@Override
public void delete(Customer customer) {
	// TODO Auto-generated method stub
	cusdao.delete(customer);
}
@Override
public void update(Customer customer) {
	// TODO Auto-generated method stub
	cusdao.update(customer);
}

}
