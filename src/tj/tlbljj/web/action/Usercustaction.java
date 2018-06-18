package tj.tlbljj.web.action;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

import tj.tlbljj.domain.Pagebean;
import tj.tlbljj.domain.User;
import tj.tlbljj.domain.Usercus;
import tj.tlbljj.service.Usercusservice;

@Controller(value="usercusaction")
@Scope(value="prototype")
public class Usercustaction extends ActionSupport implements ModelDriven<Usercus> {

	private static final long serialVersionUID = 4766028247753003081L;

	private Usercus usercus=new Usercus();
	@Override
	public Usercus getModel() {
		return usercus;
	}
	
	@Resource(name="usercusservice")
	private Usercusservice usercusservice;
	
	private Integer currentpage=1;
	private Integer pagesize=2;
	public void setCurrentpage(Integer currentpage) {
		if (currentpage==null) {
			currentpage=1;
		}
		this.currentpage = currentpage;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	
	public String save(){
		//分页查询客户拜访数据
		User u=(User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(u==null){
			return "login";
		}else{
			usercus.setUser(u);
			usercusservice.save(usercus);
		}
		return "page2";
	}
	
	public String findbypage(){
		//分页查询客户拜访数据
		User u=(User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(u==null){
			return "login";
		}else{
			DetachedCriteria criteria=DetachedCriteria.forClass(Usercus.class);
			//封装user查询条件
			criteria.add(Restrictions.eqOrIsNull("user.user_id", u.getUser_id()));
			Pagebean<Usercus> page=usercusservice.findbypage(currentpage,pagesize,criteria);
			ValueStack vs = ActionContext.getContext().getValueStack();
			vs.set("page", page);
		}
		return "usercuspage";
	}
	
	
}
