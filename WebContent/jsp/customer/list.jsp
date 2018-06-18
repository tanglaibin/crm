<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>客户列表</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
<SCRIPT language=javascript>

	function to_page(page){
		if(page){
			$("#page").val(page);
		}
		document.customerForm.submit();
		
	}
	
	$(function(){
		//页面加载后 异步加载客户数据
		var url="${pageContext.request.contextPath }/dict_findbycode.action";
		var param={"dict_type_code":"006"};
		$.post(url,param,function(data){
			//根据写回的json  遍历展示
			$(data).each(function(i,n){
				//model已经在值栈中
				if ("${model.level.dict_id}"==n.dict_id) {
					$("#cuslevel").append("<option value='"+n.dict_id+"' selected>"+n.dict_item_name+"</option>")
				}else{
				$("#cuslevel").append("<option value='"+n.dict_id+"'>"+n.dict_item_name+"</option>")
				}
			});
		},"json");
		
		var param={"dict_type_code":"002"};
		$.post(url,param,function(data){
			//根据写回的json  遍历展示
			$(data).each(function(i,n){
				if ("${model.source.dict_id}"==n.dict_id) {
					$("#cussource").append("<option value='"+n.dict_id+"' selected>"+n.dict_item_name+"</option>")
				}else{
				$("#cussource").append("<option value='"+n.dict_id+"'>"+n.dict_item_name+"</option>")
				}
			});
		},"json");
		
	});
	
</SCRIPT>

<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<FORM id="customerForm" name="customerForm"
		action="${pageContext.request.contextPath }/customer_findbypage.action"
		method=post>
		
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg"
						border=0></TD>
					<TD width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg"
						height=20></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15 background=${pageContext.request.contextPath }/images/new_022.jpg><IMG
						src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：客户管理 &gt; 客户列表</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						<TABLE borderColor=#cccccc cellSpacing=0 cellPadding=0
							width="100%" align=center border=0>
							<TBODY>
								<TR>
									<TD height=25>
										<TABLE cellSpacing=0 cellPadding=2 border=0>
											<TBODY>
												<TR>
													<TD>客户名称：</TD>
													<TD><INPUT class=textbox id=sChannel2
														style="WIDTH: 80px" value="${model.cust_name }" maxLength=50 name="cust_name"></TD>
													
													<TD>客户级别：</TD>
													<TD>
                                                    <select name="level.dict_id" id="cuslevel">
                                                    <option value="">-请选择-</option>
                                                    </select>
                                                    </TD>
														
													<TD>客户来源：</TD>
													<TD>
                                                    <select name="source.dict_id" id="cussource">
                                                    <option value="">-请选择-</option>
                                                    </select>
                                                    </TD>
														
													<TD><INPUT class=button id=sButton2 type=submit
														value=" 筛选 " name=sButton2></TD>
												</TR>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
							    
								<TR>
									<TD>
										<TABLE id=grid
											style="BORDER-TOP-WIDTH: 0px; FONT-WEIGHT: normal; BORDER-LEFT-WIDTH: 0px; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-WIDTH: 0px; BORDER-BOTTOM-COLOR: #cccccc; WIDTH: 100%; BORDER-TOP-COLOR: #cccccc; FONT-STYLE: normal; BACKGROUND-COLOR: #cccccc; BORDER-RIGHT-WIDTH: 0px; TEXT-DECORATION: none; BORDER-RIGHT-COLOR: #cccccc"
											cellSpacing=1 cellPadding=2 rules=all border=0>
											<TBODY>
												<TR
													style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
													<TD>客户名称</TD>
													 <TD>客户级别</TD>
													<TD>客户来源</TD> 
													<TD>联系人</TD>
													<TD>电话</TD>
													<TD>手机</TD>
													<TD>操作</TD>
												</TR>
												<c:forEach items="${page.list }" var="customer">
												<TR
													style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
													<TD>${customer.cust_name }</TD>
													 <TD>${customer.level.dict_item_name }</TD>
													<TD>${customer.source.dict_item_name }</TD> 
													<TD>${customer.cust_linkman }</TD>
													<TD>${customer.cust_phone }</TD>
													<TD>${customer.cust_mobile }</TD>
													<TD>
													<a href="${pageContext.request.contextPath }/customer_editui.action?cust_id=${customer.cust_id}">修改</a>
													&nbsp;&nbsp;
													<a href="${pageContext.request.contextPath }/customer_delete.action?cust_id=${customer.cust_id}"  onclick="return window.confirm('确定删除吗?')">删除</a>
													</TD>
												</TR>
												
												</c:forEach>

											</TBODY>
										</TABLE>
									</TD>
								</TR>
								
								<TR>
									<TD><SPAN id=pagelink>
											<DIV
												style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right">
												共[<B>${page.totalcount}</B>]条记录,共[<B>${page.totalpage}</B>]页
												,
												每页显示
												<select name="pagesize">
												<option value="2"<c:if test="${page.pagesize==2 }">selected</c:if>>2</option>
												<option value="3"<c:if test="${page.pagesize==3 }">selected</c:if>>3</option>
												</select>
												条
												<c:if test="${page.currentpage>1}">
												[<A href="javascript:to_page(${page.currentpage-1})">前一页</A>]
												</c:if>
												<B>${page.currentpage}</B>
												<c:if test="${page.currentpage<page.totalpage}">
												[<A href="javascript:to_page(${page.currentpage+1})">后一页</A>] 
												</c:if>
												到
												<input type="text" size="3" id="page" name="currentpage" />
												页
												<input type="button" value="Go" onclick="to_page()"/>
											</DIV>
									</SPAN></TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg"><IMG
						src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_024.jpg"
						border=0></TD>
					<TD align=middle width="100%"
						background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
	</FORM>
</BODY>
</HTML>
