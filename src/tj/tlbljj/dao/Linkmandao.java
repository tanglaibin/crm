package tj.tlbljj.dao;

import org.hibernate.criterion.DetachedCriteria;

import tj.tlbljj.domain.Linkman;
import tj.tlbljj.domain.Pagebean;

public interface Linkmandao {

	Pagebean<Linkman> findbypage(Integer currentpage, Integer pagesize, DetachedCriteria criteria);

	void save(Linkman linkman);
	Linkman findbyid(String lkm_id);

	void delete(Linkman linkman);

	void update(Linkman linkman);

}
