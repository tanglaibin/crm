package tj.tlbljj.dao;

import tj.tlbljj.domain.User;

public interface Userdao {

	User findbycode(String user_code);

	void saveu(User user);

	User getuser(User user);



}
