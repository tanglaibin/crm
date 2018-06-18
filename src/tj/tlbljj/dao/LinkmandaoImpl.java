package tj.tlbljj.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import tj.tlbljj.domain.Customer;
import tj.tlbljj.domain.Linkman;
import tj.tlbljj.domain.Pagebean;

public class LinkmandaoImpl extends HibernateDaoSupport implements Linkmandao {

	@Override
	public Pagebean<Linkman> findbypage(Integer currentpage, Integer pagesize, DetachedCriteria criteria) {
//封装pagebean  返回action
		Pagebean<Linkman> page=new Pagebean<>();
		page.setCurrentpage(currentpage);
		page.setPagesize(pagesize);
		//totalcount
		criteria.setProjection(Projections.rowCount());
		List<Number> list = (List<Number>) this.getHibernateTemplate().findByCriteria(criteria);
		if(list!=null && list.size()>0){
			int totalcount= list.get(0).intValue();
			page.setTotalcount(totalcount);
		}
		//list
		criteria.setProjection(null);
		//list
		List<Linkman> list2 = (List<Linkman>) this.getHibernateTemplate().findByCriteria(criteria, (currentpage-1)*pagesize, pagesize);
		page.setList(list2);
		return page;
	}

	@Override
	public void save(Linkman linkman) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(linkman);
	}

	@Override
	public Linkman findbyid(String lkm_id) {
		// TODO Auto-generated method stub
		List<Linkman> list = (List<Linkman>) this.getHibernateTemplate().find("from Linkman where lkm_id=?", lkm_id);
		if (list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void delete(Linkman linkman) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(linkman);
	}

	@Override
	public void update(Linkman linkman) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(linkman);
	}


}
