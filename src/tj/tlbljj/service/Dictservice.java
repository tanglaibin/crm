package tj.tlbljj.service;

import java.util.List;

import tj.tlbljj.domain.Dict;

public interface Dictservice {

	List<Dict> findbycode(String dict_type_code);

}
