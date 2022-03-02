<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="/css/splash.css">
		<title>All Books</title>
	</head>
	<body>
	    <header>
	        <div>
	            <h1>Welcome <c:out value="${user.get().name}"/></h1>
	            <a href="/logout">Log out</a>
	        </div>
	        <div>
	            <p>Books from everyone's shelves</p>
	            <a href="/addBook">Add a book to your shelf!</a>
	        </div>
	    </header>
	    <div>
         <table class="table table-bordered">
             <thead>
                 <tr>
                     <th scope="col">ID</th>
                     <th scope="col">Title</th>
                     <th scope="col">Author Name</th>
                     <th scope="col">Posted By</th>
                 </tr>
              
              </thead>
              <c:forEach var="book" items="${books}">
	              <tbody>
	                  <tr>
	                      <th scope="row"><c:out value="${book.id}"/></th> 
	                      <td><a href="/books/${book.id}"><c:out value="${book.title}"/></a></td>
	                      <td><c:out value="${book.authorName}"/></td>
	                      <td><c:out value="${book.thoughts}"/></td>
	                      <c:if test = "${book.getUser().id eq userId.get().id}">
	                      <td>
	                          <form action="/delete/${book.id}" method="post">
	                              <input type="hidden" name="_method" value="delete">
	                              <input type="submit" value="Delete">
	                          </form>
	                      </td>
	                      </c:if> 
	                  </tr>
	              </tbody>
              </c:forEach>
         </table>
     </div>
	</body>
</html>