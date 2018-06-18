package tj.tlbljj.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

import tj.tlbljj.domain.Customer;
import tj.tlbljj.domain.Dict;
import tj.tlbljj.domain.Pagebean;
import tj.tlbljj.service.Customerservice;
import tj.tlbljj.utils.FastJsonUtil;
import tj.tlbljj.utils.uuidnameutlis;

public class Customeraction extends ActionSupport implements ModelDriven<Customer> {

	private static final long serialVersionUID = 993220167115047071L;
	private Customer customer =new Customer();
	public Customer getModel() {
		return customer;
	}

	private Customerservice cusservice;
	public void setCusservice(Customerservice cusservice) {
		this.cusservice = cusservice;
	}

	private Integer currentpage=1;
	private Integer pagesize=2;
	public void setCurrentpage(Integer currentpage) {
		if(currentpage==null){
			currentpage=1;
		}
		this.currentpage = currentpage;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}


	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	
	
	
	public String findbypage(){
		//分页查询客户数据  查询数据 封装进pagebean 放入值栈  取出展示
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		//封装离线查询条件  客户姓名   级别  来源 
		if (customer.getCust_name()!=null && !customer.getCust_name().trim().isEmpty()) {
			criteria.add(Restrictions.like("cust_name", "%"+customer.getCust_name()+"%"));}
		Dict level=customer.getLevel();
		if (level!=null && !level.getDict_id().trim().isEmpty()) {
			criteria.add(Restrictions.eq("level.dict_id", level.getDict_id()));}
		Dict source=customer.getSource();
		if (source!=null && !source.getDict_id().trim().isEmpty()) {
			criteria.add(Restrictions.eq("source.dict_id", source.getDict_id()));}
		Pagebean<Customer> pagebean=cusservice.findbypage(currentpage,pagesize,criteria);
		//放入域中  压栈运行
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("page", pagebean);
		return "page";}
	
	
	public String save() throws IOException{
    //保存客户  文件资料上传   封装数据   文件处理   C:\Users\tlb\Desktop\java\3，tomcat\apache-tomcat-7.0.52\image 
		//分有上传文件  与无上传文件  
		if (uploadFileName!=null) {
			String path="C:\\Users\\tlb\\Desktop\\java\\3，tomcat\\apache-tomcat-7.0.52\\image\\";
			String lastname=uuidnameutlis.getname(uploadFileName);
			File file=new File(path+lastname);
			FileUtils.copyFile(upload, file);
			customer.setFilepath(path+lastname);	
			cusservice.save(customer);
		}else{
			cusservice.save(customer);
		}
    return "page2";
	}
	
	
	public String delete(){
		//通过custid查处customer  判断有无上传文件  分情况删除
		customer=cusservice.findbyid(customer.getCust_id());
		String path=customer.getFilepath();
		if (path!=null && path!="") {
			File file=new File(path);
			file.delete();
			cusservice.delete(customer);
		}else{
			cusservice.delete(customer);
		}
		return "page2";
	}
	
	public String editui(){
		customer=cusservice.findbyid(customer.getCust_id());
		return "edit";
	}
	
	
	public String updatecus() throws IOException{
		//通过cust_id 获取customer  根据有无上传文件    分情况更新数据库【现在我采取的是就数据有无path】
		//分有无上传文件2种情况处理  再分是否存在旧文件处理
		String path=customer.getFilepath();
		if (uploadFileName!=null) {
		if (path!=null && !path.trim().isEmpty()) {
				File file=new File(path);
				file.delete();
				String path2="C:\\Users\\tlb\\Desktop\\java\\3，tomcat\\apache-tomcat-7.0.52\\image\\";
				String lastname=uuidnameutlis.getname(uploadFileName);
				File file2=new File(path2+lastname);
				FileUtils.copyFile(upload,file2);
				customer.setFilepath(path2+lastname);
				cusservice.update(customer);
			}else{
				String path2="C:\\Users\\tlb\\Desktop\\java\\3，tomcat\\apache-tomcat-7.0.52\\image\\";
				String lastname=uuidnameutlis.getname(uploadFileName);
				File file2=new File(path2+lastname);
				FileUtils.copyFile(upload,file2);
				customer.setFilepath(path2+lastname);
				cusservice.update(customer);
			}
	}else{
		cusservice.update(customer);
	}
		return "page2";
		}
		
	public String findall(){
		//查询客户姓名  fastjson写回页面展示
		List<Customer> list=cusservice.findall();
		String jsonString = FastJsonUtil.toJSONString(list);
		HttpServletResponse response = ServletActionContext.getResponse();
		FastJsonUtil.write_json(response, jsonString);
		return NONE;
	}
	
}
