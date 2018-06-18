package tj.tlbljj.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import tj.tlbljj.dao.Dictdao;
import tj.tlbljj.domain.Dict;

@Transactional
public class DictserviceImpl implements Dictservice {
private Dictdao dictdao;
public void setDictdao(Dictdao dictdao) {
	this.dictdao = dictdao;
}
@Override
public List<Dict> findbycode(String dict_type_code) {
	return dictdao.findbycode(dict_type_code);
}

}
