package tj.tlbljj.service;

import org.hibernate.criterion.DetachedCriteria;

import tj.tlbljj.domain.Pagebean;
import tj.tlbljj.domain.Usercus;

public interface Usercusservice {

	void save(Usercus usercus);

	Pagebean<Usercus> findbypage(Integer currentpage, Integer pagesize, DetachedCriteria criteria);

}
