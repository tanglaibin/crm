package tj.tlbljj.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import tj.tlbljj.domain.User;
import tj.tlbljj.utils.MD5Utils;

public class UserdaoImpl extends HibernateDaoSupport implements Userdao {

	public User findbycode(String user_code) {
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where user_code=?", user_code);
		if (list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	public void saveu(User user) {
		this.getHibernateTemplate().save(user);		
	}

	@Override
	public User getuser(User user) {
        //qbc离线查询  拼接查询条件
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("user_code", user.getUser_code()));
		criteria.add(Restrictions.eq("user_password", MD5Utils.md5(user.getUser_password())));
		criteria.add(Restrictions.eq("user_state", "1"));
		
		List<User> list = (List<User>) this.getHibernateTemplate().findByCriteria(criteria);
		if (list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}

}
