<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/frameset.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>

<META http-equiv=Content-Type content="text/html; charset=utf-8">
<STYLE type=text/css>
BODY {
	FONT-SIZE: 12px; COLOR: #ffffff; FONT-FAMILY: 宋体
}
TD {
	FONT-SIZE: 12px; COLOR: #ffffff; FONT-FAMILY: 宋体
}
.error{
color:red;
}
</STYLE>

<META content="MSHTML 6.00.6000.16809" name=GENERATOR>

<script type="text/javascript">
function checkcode(){
	//空名    重名校验
	var user_code=$("#user_code").val();
	if (user_code.trim()=="") {
		$("#usercode").addClass("error");
		$("#usercode").html("登录名不能为空");
	}
	else{
		//如果不为空  ajax异步校验
		var url="${pageContext.request.contextPath }/user_checkcode.action";
		var param={"user_code":user_code};
		$.post(url,param,function(data){
			//根据后台返回的值  提示
			if(data && data=="yes"){
				$("#usercode").removeClass("error");
				$("#usercode").html("可以注册");
			}else{
				$("#usercode").addClass("error");
				$("#usercode").html("用户已存在，不可以注册");
			}
		})
	}
}

function checkForm(){
	checkcode();
	if($(".error").size() > 0){
		return false;
	}
}

</script>


</HEAD>
<BODY>
<FORM id=form1 name=form1 action="${pageContext.request.contextPath }/user_regist.action" onsubmit="return checkForm()"  method=post>

<DIV id=UpdatePanel1>
<DIV id=div1 
style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>
<DIV id=div2 
style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>


<DIV>&nbsp;&nbsp; </DIV>
<DIV>
<TABLE cellSpacing=0 cellPadding=0 width=900 align=center border=0>
  <TBODY>
  <TR>
    <TD style="HEIGHT: 105px"><IMG src="images/login_1.gif" 
  border=0></TD></TR>
  <TR>
    <TD background=images/login_2.jpg height=300>
      <TABLE height=300 cellPadding=0 width=900 border=0>
        <TBODY>
        <TR>
          <TD colSpan=2 height=35></TD></TR>
        <TR>
          <TD width=360></TD>
          <TD>
            <TABLE cellSpacing=0 cellPadding=2 border=0>
              <TBODY>
              <TR>
                <TD style="HEIGHT: 28px" width=80>登 录 名：</TD>
                <TD style="HEIGHT: 28px" width=150>
                <INPUT id="user_code"  type="text" style="WIDTH: 130px" name="user_code" onblur="checkcode()"></TD>
                <TD style="HEIGHT: 28px" width=370>
                <SPAN 
                  id="usercode" 
                  style="FONT-WEIGHT: bold; "></SPAN></TD></TR>
              <TR>
                <TD style="HEIGHT: 28px">登录密码：</TD>
                <TD style="HEIGHT: 28px">
                <INPUT id="user_password" style="WIDTH: 130px" 
                  type=password name="user_password"></TD>
               </TR>
               
              <TR>
                <TD style="HEIGHT: 28px">姓名：</TD>
                <TD style="HEIGHT: 28px">
                <INPUT id=username style="WIDTH: 130px" 
                  type="text" name="user_name"></TD>
                <TD style="HEIGHT: 28px">
                <SPAN id=username   style="FONT-WEIGHT: bold; "></SPAN></TD></TR>

              <TR>
                <TD style="HEIGHT: 18px"></TD>
                <TD style="HEIGHT: 18px"></TD>
                <TD style="HEIGHT: 18px"></TD></TR>
              <TR>
                <TD></TD>
                <TD>
                <INPUT type="submit" value="点击注册"> 
                  
              </TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR>
  <TR>
    <TD><IMG src="images/login_3.jpg" 
border=0></TD></TR></TBODY></TABLE></DIV></DIV>


</FORM></BODY></HTML>


</body>
</html>