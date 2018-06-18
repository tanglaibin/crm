package tj.tlbljj.service;

import org.springframework.transaction.annotation.Transactional;

import tj.tlbljj.dao.Userdao;
import tj.tlbljj.domain.User;

@Transactional
public class UserserviceImpl implements Userservice {
	
private Userdao userdao;
public void setUserdao(Userdao userdao) {
	this.userdao = userdao;
}

public User findbycode(String user_code) {

	return userdao.findbycode(user_code);
}

public void saveu(User user) {
	userdao.saveu(user);
}

@Override
public User getuser(User user) {
	// TODO Auto-generated method stub
	return userdao.getuser(user);
}

}
