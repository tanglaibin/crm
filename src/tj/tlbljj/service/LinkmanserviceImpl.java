package tj.tlbljj.service;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import tj.tlbljj.dao.Linkmandao;
import tj.tlbljj.domain.Linkman;
import tj.tlbljj.domain.Pagebean;

@Transactional
public class LinkmanserviceImpl implements Linkmanservice {
	private Linkmandao linkmandao;
	public void setLinkmandao(Linkmandao linkmandao) {
		this.linkmandao = linkmandao;
	}
	@Override
	public Pagebean<Linkman> findbypage(Integer currentpage, Integer pagesize, DetachedCriteria criteria) {
		// TODO Auto-generated method stub
		return linkmandao.findbypage(currentpage,pagesize,criteria);
	}
	@Override
	public void save(Linkman linkman) {
		// TODO Auto-generated method stub
		linkmandao.save(linkman);
	}
	@Override
	public Linkman findbyid(String lkm_id) {
		// TODO Auto-generated method stub
		return linkmandao.findbyid(lkm_id);
	}
	@Override
	public void delete(Linkman linkman) {
		// TODO Auto-generated method stub
		linkmandao.delete(linkman);
	}
	@Override
	public void update(Linkman linkman) {
		// TODO Auto-generated method stub
		linkmandao.update(linkman);
	}
	
}
