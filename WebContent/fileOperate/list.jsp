<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html  
PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"  
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
<title>Insert title here</title>  
</head>  
<body>  
    <c:forEach items="${result }" var="item">  
        <c:forEach items="${item }" var="m">  
            <c:if test="${m.key eq 'realName' }">  
                ${m.value }  
            </c:if>  
            <br />  
        </c:forEach>  
    </c:forEach>  
</body>  
</html> 