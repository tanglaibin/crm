package tj.tlbljj.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import tj.tlbljj.domain.Customer;
import tj.tlbljj.domain.Pagebean;
import tj.tlbljj.domain.Usercus;

@Repository(value="usercusdao")
public class UsercusdaoImpl extends HibernateDaoSupport implements usercusdao {
	
	@Resource(name="sessionFactory")
	public void set2sessionfac(SessionFactory sessionFactory){
			super.setSessionFactory(sessionFactory);
		}

	@Override
	public void save(Usercus usercus) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(usercus);
	}

	@Override
	public Pagebean<Usercus> findbypage(Integer currentpage, Integer pagesize, DetachedCriteria criteria) {
		Pagebean<Usercus> page=new Pagebean<Usercus>();
		page.setCurrentpage(currentpage);
		page.setPagesize(pagesize);
		//count
		criteria.setProjection(Projections.rowCount());
		List<Number> list = (List<Number>) this.getHibernateTemplate().findByCriteria(criteria);
		if(list!=null && list.size()>0){
			int totalcount= list.get(0).intValue();
			page.setTotalcount(totalcount);
		}
		criteria.setProjection(null);
		List<Usercus> list2 = (List<Usercus>) this.getHibernateTemplate().findByCriteria(criteria, (currentpage-1)*pagesize, pagesize);
		page.setList(list2);
		
		return page;
	}
}
