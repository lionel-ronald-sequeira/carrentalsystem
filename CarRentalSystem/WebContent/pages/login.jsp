<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form"   prefix="form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1"/>
	<title>Car Rental System</title>
	<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/bootstrap-theme.css" />" rel="stylesheet">
	<script src="<c:url value="/resources/js/jquery-2.1.4.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.js" />"></script>
</head>
<body>
	<div class="container" >
		<div class="row">
			<div class="col-lg-4">
			</div>
			<div class="col-lg-4">
				<div class="panel panel-primary" style="margin-top:180px;">
					<div class="panel-heading">SignIn</div>
					<div class="panel-body">
						<c:if test="${not empty error}">
							<div class="row">
								<div class="col-lg-12">
									<div class="alert alert-danger" role="alert">${error}</div>
								</div>
							</div>
						</c:if>
						<form:form method="POST" modelAttribute="loginCredential" action="${ctx}/home">
							<div class="form-group">
								<label>Email address</label>
								<form:input path="userName"  type="email" class="form-control" placeholder="Email" />
							</div>
							<div class="form-group">
								<label>Password</label>
								<form:input path="password" type="password" class="form-control" placeholder="Password"/>
							</div>
							<div class="pull-right">
								<button type="submit" class="btn btn-primary" >Submit</button>
							</div>
						</form:form>
					</div>
				</div>
			</div>
			<div class="col-lg-4">
			</div>
		</div>  
	</div>


</body>
</html>