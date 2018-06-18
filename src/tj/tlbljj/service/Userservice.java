package tj.tlbljj.service;

import tj.tlbljj.domain.User;

public interface Userservice {

	User findbycode(String user_code);

	void saveu(User user);

	User getuser(User user);

}
