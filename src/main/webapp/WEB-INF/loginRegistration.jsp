<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Book Club</title>
	</head>
	<body>
    	<h1>Book Club</h1>
    	<h2>A place for friends to share thoughts on books.</h2>
    	<div>
    		<div>
	        	<form:form action="/registerUser" method="post" modelAttribute="user"> <!-- Register -->
	            	<form:label path="name">Name</form:label>
	            	<form:errors path="name"/>
	           		<form:input path="name"/>
	           		<form:label path="email">Email</form:label>
	            	<form:errors path="email"/>
	            	<form:input path="email"/>
	            	<form:label path="password">Password</form:label>
	            	<form:errors path="password"/>
	            	<form:input type="password" path="password"/>
	            	<form:label path="confirm">Confirm Password</form:label>
	            	<form:input type="password" path="confirm"/>
	            	<input type="submit" value="Register"/>
	        	</form:form>
        	</div>
        	<div>
	        	<form:form action="/loginUser" method="post" modelAttribute="userLogin"><!-- Login -->
	            	<form:errors path="email"/>
	            	<form:label path="email">Email</form:label>
	            	<form:input path="email"/>
	            	<form:label path="password">Password</form:label>
	            	<form:input type="password" path="password"/>
	            	<input type="submit" value="Login"/>
	        	</form:form>
        	</div> 
    	</div>
	</body>
</html>