<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
</head>
<body>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<div align="center">
    <FORM action="login" method="post">
        <TABLE border="0" cellspacing="0" cellpadding="7" align="center">
            <CAPTION><H3 style="color:#0000FF"><spring:message code="login.label.authentification"/></H3></CAPTION>
            <TR>
                <TD style="color:#FF0000"><spring:message code="login.label.input_email"/></TD>
                <TD><input name="username" value="bsv@gmail.com"></TD>
            </TR>
            <TR>
                <TD style="color:#FF0000"><spring:message code="login.label.input_passw"/></TD>
                <TD><input name="password" value=""></TD>
            </TR>
            <TR>
                <TD colspan="2" align="center"><input type="submit" value="<spring:message code="login.button.login"/>" style="color:#0000FF" align="center"></TD>
            </TR>
        </TABLE>
    </FORM>
</div>
</body>
</html>