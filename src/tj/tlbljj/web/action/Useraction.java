package tj.tlbljj.web.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import tj.tlbljj.domain.User;
import tj.tlbljj.service.Userservice;
import tj.tlbljj.utils.MD5Utils;

public class Useraction extends ActionSupport implements ModelDriven<User>{

	private static final long serialVersionUID = -1759975731794153128L;
	
	private User user=new User();
	public User getModel() {
		return user;
	}
	
	//注入service
	private Userservice userservice;
	public void setUserservice(Userservice userservice) {
		this.userservice = userservice;
	}
	

	
	public String checkcode() throws IOException{
		//重名校验
		User u=userservice.findbycode(user.getUser_code());
		//System.out.println(u);
		//获取response 
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		if (u==null) {
			//向浏览器写回yes
			writer.print("yes");
		}else{
			//向浏览器写回no
			writer.print("no");
		}
		return NONE;
	}
	
	public String regist(){
		//注册用户  密码加密
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		user.setUser_state("1");
		userservice.saveu(user);
		return "login";
	}
	
	//用户登陆  根据数据查找数据库  找到 返回首页  user放入值栈    首页取出user名称
	public String login(){
		//根据现有数据  查询数据库 得到user  
		User u=userservice.getuser(user);
		if (u==null) {
			ServletActionContext.getRequest().getSession().setAttribute("msg", "用户名或密码错误");
			return "login";
		}else{
			//放入session
			ServletActionContext.getRequest().getSession().removeAttribute("msg");
			ServletActionContext.getRequest().getSession().setAttribute("user", u);
		}
		return "index";
	}
	
	public String logout(){
			ServletActionContext.getRequest().getSession().removeAttribute("user");
		return "index";
	}
	
}
