<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form"   prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Information</title>
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/bootstrap-theme.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-2.1.4.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.js" />"></script>
</head>
<body>
	<%@ include file="header.jsp" %>
	<div class="container-fluid">
		<div class="panel panel-primary" style="margin-top:100px;">
			<div class="panel-heading">Add Customer Information</div>
			<div class="panel-body">
				<c:if test="${not empty msg}">
					<div class="row">
						<div class="col-lg-12">
							<div class="alert alert-success" role="alert">${msg}</div>
						</div>
					</div>
				</c:if>
				<form:form method="POST" modelAttribute="customerinfo" action="${ctx}/addcustomerinfo">
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label>FirstName<span style="color:red">*</span></label>
								<form:input path="firstName" type="text" class="form-control" placeholder="FirstName"/>
				    		</div>
				    	</div>
				    	<div class="col-lg-3">
				    		<div class="form-group">
								<label>LastName<span style="color:red">*</span></label>
								<form:input path="lastName" type="text" class="form-control" placeholder="LastName"/>
				    		</div>
				    	</div>
				    	<div class="col-lg-3">
				    		<div class="form-group">
								<label>PhoneNo<span style="color:red">*</span></label>
								<form:input path="phoneNo" type="text" class="form-control" placeholder="PhoneNo"/>
				    		</div>
				    	</div>
				    	<div class="col-lg-3">
				    		<div class="form-group">
								<label>Street<span style="color:red">*</span></label>
								<form:input path="street" type="text" class="form-control" placeholder="Street"/>
				    		</div>
				    	</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label>State<span style="color:red">*</span></label>
								<form:input path="state" type="text" class="form-control" placeholder="State"/>
				    		</div>
				    	</div>
				    	<div class="col-lg-3">
				    		<div class="form-group">
								<label>ZipCode<span style="color:red">*</span></label>
								<form:input path="zipcode" type="text" class="form-control" placeholder="ZipCode"/>
				    		</div>
				    	</div>
				    	<div class="col-lg-3">
				    		<div class="form-group">
				    			<button type="submit" class="btn btn-primary btn-lg" style="margin-top:25px;">Add</button>
				    		</div>
				        </div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="panel panel-primary" style="margin-top:25px;">
			<div class="panel-heading">Customer Information</div>
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover">
						<c:if test="${empty customerList}">
							<div class="row">
								<div class="col-lg-12">
									<div class="alert alert-danger" role="alert">Customer Information Not Available</div>
								</div>
							</div>
						</c:if>
						<c:if test="${not empty customerList}">
							<tr>
								<th>Customer Id</th>
								<th>FirstName</th>
								<th>LastName</th>
								<th>PhoneNo</th>
								<th>Street</th>
								<th>State</th>
								<th>ZipCode</th>
							</tr>
							<c:forEach var="customerObj" items="${customerList}">
								<tr>
									<td>${customerObj.customerId}</td>
									<td>${customerObj.firstName}</td>
									<td>${customerObj.lastName}</td>
									<td>${customerObj.phoneNo}</td>
									<td>${customerObj.street}</td>
									<td>${customerObj.state}</td>
									<td>${customerObj.zipcode}</td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>