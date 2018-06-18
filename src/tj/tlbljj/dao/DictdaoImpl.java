package tj.tlbljj.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import tj.tlbljj.domain.Dict;

public class DictdaoImpl extends HibernateDaoSupport implements Dictdao {

	@Override
	public List<Dict> findbycode(String dict_type_code) {
		List<Dict> list = (List<Dict>) this.getHibernateTemplate().find("from Dict where dict_type_code=?", dict_type_code);
		return list;
	}

}
