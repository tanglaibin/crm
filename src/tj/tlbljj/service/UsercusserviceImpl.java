package tj.tlbljj.service;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tj.tlbljj.dao.usercusdao;
import tj.tlbljj.domain.Pagebean;
import tj.tlbljj.domain.Usercus;

@Service(value="usercusservice")
@Transactional
public class UsercusserviceImpl implements Usercusservice {
	@Resource(name="usercusdao")
	private usercusdao usercusdao;

	@Override
	public void save(Usercus usercus) {
		// TODO Auto-generated method stub
		usercusdao.save(usercus);
	}

	@Override
	public Pagebean<Usercus> findbypage(Integer currentpage, Integer pagesize, DetachedCriteria criteria) {
		// TODO Auto-generated method stub
		return usercusdao.findbypage(currentpage,pagesize,criteria);
	}
}
