<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="me" uri="/WEB-INF/template"%>

<me:bg title="Publsher">
	<jsp:attribute name="content">
		<!--start html -->
		<div class="page-header">Add Publisher</div>
		<form method="post" class="form">
			<input type="hidden" name="id" value="${o.id}">
			<div>
				<label>Name</label>
				<input type="text" name="name" value="${o.name}">
			</div>
			<div>
				<button class="btn btn-primary">Save</button>
			</div>
		</form>
		
		<c:if test="${not empty msg}">
			<p class="error">${msg}</p>
		</c:if>
		
		<!--end html  -->
	</jsp:attribute>
</me:bg>