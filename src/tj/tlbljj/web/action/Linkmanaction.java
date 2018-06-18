package tj.tlbljj.web.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

import tj.tlbljj.domain.Customer;
import tj.tlbljj.domain.Linkman;
import tj.tlbljj.domain.Pagebean;
import tj.tlbljj.service.Linkmanservice;

public class Linkmanaction extends ActionSupport implements ModelDriven<Linkman> {

	private static final long serialVersionUID = -2982132076955346711L;
	private Linkman linkman=new Linkman();
	@Override
	public Linkman getModel() {
		// TODO Auto-generated method stub
		return linkman;
	}
	
	private Linkmanservice linkmanservice;
	public void setLinkmanservice(Linkmanservice linkmanservice) {
		this.linkmanservice = linkmanservice;
	}
	
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

	public String findbypage(){
		//分页查询联系人数据   dao层封装pagebean  放入值栈 页面遍历取出
		DetachedCriteria criteria=DetachedCriteria.forClass(Linkman.class);
		//封装搜索条件  离线查询筛选结果
		if (linkman.getLkm_name()!=null && !linkman.getLkm_name().trim().isEmpty()) {
			criteria.add(Restrictions.like("lkm_name", "%"+linkman.getLkm_name()+"%"));
		}
		
		Customer customer=linkman.getCustomer();
		//System.out.println(customer);
		if (customer!=null && !customer.getCust_id().trim().isEmpty()) {
			criteria.add(Restrictions.eq("customer.cust_id", customer.getCust_id()));
		}
		
		Pagebean<Linkman> page=linkmanservice.findbypage(currentpage,pagesize,criteria);
		//System.out.println(1);
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("page", page);
		return "linkpage";
	}
	

	public String save(){
		//分页查询联系人数据   dao层封装pagebean  放入值栈 页面遍历取出
		linkmanservice.save(linkman);
		return "linkpage2";
	}
	
	public String delete(){
		linkman=linkmanservice.findbyid(linkman.getLkm_id());
		linkmanservice.delete(linkman);
		return "linkpage2";
	}
	
	public String ediatui(){
		linkman=linkmanservice.findbyid(linkman.getLkm_id());
		return "editui";
	}
	
	public String update(){
		//根据id 更新数据库？
		linkmanservice.update(linkman);
		return "linkpage2";
	}
	
	
}
