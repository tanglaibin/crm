package tj.tlbljj.dao;

import java.util.List;

import tj.tlbljj.domain.Dict;

public interface Dictdao {

	List<Dict> findbycode(String dict_type_code);

}
