package tj.tlbljj.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import tj.tlbljj.domain.Customer;
import tj.tlbljj.domain.Pagebean;

public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao{

	@Override
	public Pagebean<Customer> findbypage(Integer currentpage, Integer pagesize, DetachedCriteria criteria) {

		Pagebean<Customer> page=new Pagebean<Customer>();
		page.setCurrentpage(currentpage);
		page.setPagesize(pagesize);
		//count
		criteria.setProjection(Projections.rowCount());
		List<Number> list = (List<Number>) this.getHibernateTemplate().findByCriteria(criteria);
		if(list!=null && list.size()>0){
			int totalcount= list.get(0).intValue();
			page.setTotalcount(totalcount);
		}
		//清空查询条件
		criteria.setProjection(null);
		//list
		List<Customer> list2 = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria, (currentpage-1)*pagesize, pagesize);
		page.setList(list2);
		
		return page;
	}

	@Override
	public List<Customer> findall() {
		List<Customer> list = (List<Customer>) this.getHibernateTemplate().find("from Customer");
		return list;
	}

	@Override
	public void save(Customer customer) {
this.getHibernateTemplate().save(customer);		
	}

	@Override
	public Customer findbyid(String cust_id) {
		List<Customer> list = (List<Customer>) this.getHibernateTemplate().find("from Customer where cust_id = ?", cust_id);
		return list.get(0);
	}

	@Override
	public void delete(Customer customer) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(customer);
	}

	@Override
	public void update(Customer customer) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(customer);
	}

}
