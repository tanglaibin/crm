package tj.tlbljj.web.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import tj.tlbljj.domain.Dict;
import tj.tlbljj.service.Dictservice;
import tj.tlbljj.utils.FastJsonUtil;

public class Dictaction extends ActionSupport implements ModelDriven<Dict>{

	private static final long serialVersionUID = 383028699731180170L;
	private Dict dict=new Dict();
	@Override
	public Dict getModel() {
		// TODO Auto-generated method stub
		return dict;
	}

	private Dictservice dictservice;
	public void setDictservice(Dictservice dictservice) {
		this.dictservice = dictservice;
	}
	
	public String findbycode(){
		//查找dict
		List<Dict> list=dictservice.findbycode(dict.getDict_type_code());
		//将list转成string  发至页面
		HttpServletResponse response = ServletActionContext.getResponse();
		String jsonString = FastJsonUtil.toJSONString(list);
		//System.out.println(jsonString);
		FastJsonUtil.write_json(response, jsonString);
		return NONE;
	}
	
}
