<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<header>
        <div>
            <h1>Add a book to your shelf!</h1>
            <a href="/bookClub">Back to the shelves.</a>
        </div>
     </header>
	 <form:form action="/createBook" method="post" modelAttribute="book">
	 	<p>
	        <form:label path="title">Title</form:label>
	        <form:errors path="title"/>
	        <form:input path="title"/>
	    </p>
	    <p>
		    <form:label path="authorName">Author Name</form:label>
		    <form:errors path="authorName"/>
			<form:input path="authorName"/>
        </p>
        <p>
            <form:label path="thoughts">thoughts</form:label>
            <form:errors path="thoughts"/>
            <form:input path="thoughts"/>
	    </p>
	    <form:input type="hidden" path="user" value="${userId}"/>
	    <input type="submit" value="Submit"/>
     </form:form>
</body>
</html>