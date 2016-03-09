<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form"   prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
	<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/bootstrap-theme.css" />" rel="stylesheet">
	<script src="<c:url value="/resources/js/jquery-2.1.4.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.js" />"></script>
</head>
<body>
<%@ include file="header.jsp" %>
<div class="container-fluid">
		<div class="panel panel-primary" style="margin-top:100px;">
			<div class="panel-heading">Add Car Information</div>
			<div class="panel-body">
				<c:if test="${not empty msg}">
					<div class="row">
						<div class="col-lg-12">
							<div class="alert alert-success" role="alert">${msg}</div>
						</div>
					</div>
				</c:if>
				<c:if test="${not empty error}">
					<div class="row">
						<div class="col-lg-12">
							<div class="alert alert-danger" role="alert" id ="error">${error}</div>
						</div>
					</div>
				</c:if>
				<form:form method="POST" modelAttribute="carinfo" action="${ctx}/addcarinfo" id="carform">
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label>Vehicle Id<span style="color:red">*</span></label>
								<form:input path="vehicleId" type="text" class="form-control" placeholder="Vehicle Id" id="vehicleId"/>
				    		</div>
				    	</div>
				    	<div class="col-lg-3">
				    		<div class="form-group">
								<label>Model<span style="color:red">*</span></label>
								<form:input path="model" type="text" class="form-control" placeholder="Model" id="model"/>
				    		</div>
				    	</div>
				    	<div class="col-lg-3">
				    		<div class="form-group">
								<label>Year<span style="color:red">*</span></label>
								<form:input path="year" type="text" class="form-control" placeholder="Year" id="year"/>
				    		</div>
				    	</div>
				    	<div class="col-lg-3">
				    		<div class="form-group">
								<label>Daily Rate<span style="color:red">*</span></label>
								<form:input path="dailyRate" type="text" class="form-control" placeholder="Daily Rate" id="dailyRate"/>
				    		</div>
				    	</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label>Weekly Rate<span style="color:red">*</span></label>
								<form:input path="weeklyRate" type="text" class="form-control" placeholder="Weekly Rate" id="weeklyRate"/>
				    		</div>
				    	</div>
				    	<div class="col-lg-3">
				    		<div class="form-group">
								<label>Car Type<span style="color:red">*</span></label>
								<form:select path="carTypes" multiple="true" class="form-control" id="carTypes">
  									<form:option value="Compact">Compact</form:option>
  									<form:option value="Medium">Medium</form:option>
 									<form:option value="Large">Large</form:option>
  									<form:option value="SUV">SUV</form:option>
  									<form:option value="Truck">Truck</form:option>
  									<form:option value="Van">Van</form:option>
								</form:select>
				    		</div>
				    	</div>
				    	<div class="col-lg-3">
				    		<div class="form-group">
				    			<label>Owner<span style="color:red">*</span></label>
								<form:select class="form-control" path="ownerId" id="ownerIds">
									<option>Select</option>
									<c:if test="${not empty ownerList}">
										<c:forEach var="ownerObj" items="${ownerList}">
  											<option value="${ownerObj.ownerId}">${ownerObj.ownerName}</option>
  										</c:forEach>
  									</c:if>
  								</form:select>
				    		</div>
				 		</div>
				    	<div class="col-lg-3">
				    		<div class="form-group">
				    			<button type="submit" id="addbutton" class="btn btn-primary btn-lg" style="margin-top:25px;">Add</button>
				    			<button type="submit" id="updatebutton" class="btn btn-primary btn-lg" style="margin-top:25px;">Update</button>
				    		</div>
				        </div>
					</div>
				</form:form>
				<input type="hidden" id="ctxValue" value="${ctx}"/>
			</div>
		</div>
</div>
<div class="container-fluid">
		<div class="panel panel-primary" style="margin-top:25px;">
			<div class="panel-heading">Car Information</div>
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover">
						<c:if test="${empty carList}">
							<div class="row">
								<div class="col-lg-12">
									<div class="alert alert-danger" role="alert">Car Information Not Available</div>
								</div>
							</div>
						</c:if>
						<c:if test="${not empty carList}">
							<tr>
								<th>Vehicle Id</th>
								<th>Model</th>
								<th>Year</th>
								<th>Daily Rate</th>
								<th>Weekly Rate</th>
								<th>Available</th>
								<th>Compact</th>
								<th>Medium</th>
								<th>Large</th>
								<th>SUV</th>
								<th>Truck</th>
								<th>Van</th>
								<th>Action</th>
							</tr>
							<c:forEach var="carObj" items="${carList}">
								<tr>
									<td>${carObj.vehicleId}</td>
									<td>${carObj.model}</td>
									<td>${carObj.year}</td>
									<td>${carObj.dailyRate}</td>
									<td>${carObj.weeklyRate}</td>
									<td>${carObj.available}</td>
									<td>${carObj.compact}</td>
									<td>${carObj.medium}</td>
									<td>${carObj.large}</td>
									<td>${carObj.suv}</td>
									<td>${carObj.truck}</td>
									<td>${carObj.van}</td>
									<td><a href="javascript:void()" id="${carObj.vehicleId}" class="anchortag">Edit</a></td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$("#updatebutton").hide();
		
		$("#addbutton").click(function(){
			if($('#vehicleId').val()=='' || $("#model").val()=='' || $("#year").val()=='' 
					|| $("#dailyRate").val()=='' || $("#weeklyRate").val()=='' ){
				$("#error").text('Please enter details marked with *');
				$("#error").show();
				return 0;
			}
			
			$("#carform").attr("action",$("#ctxValue").val().concat("/addcarinfo"));
		});
		
		$("#updatebutton").click(function(){
			$("#vehicleId").prop('disabled',false);
			$("#carform").attr("action",$("#ctxValue").val().concat("/updatecarinfo"));
		});
	
	   $(".anchortag").click(function () {
	        var attributeValue = $(this).attr("id");
	        var data = 'vehicleId='
				+ encodeURIComponent(attributeValue);
		
	        $.ajax({
				url : $("#ctxValue").val().concat("/editcarinfo"),
				data : data,
				type : "GET",
 				success : function(response) {
					var values=response.split(",");
					$("#vehicleId").val(values[0]);
					$("#vehicleId").prop('disabled',true);
					$("#model").val(values[1]);
					$("#model").prop('disabled',true);
					$("#year").val(values[2]);
					$("#year").prop('disabled',true);
					$("#dailyRate").val(values[3]);
					$("#weeklyRate").val(values[4]);
					$("#carTypes").prop('disabled',true);
					$('[name=ownerId]').val( values[5]);
					$("#ownerIds").prop('disabled',true);
					
					$("#addbutton").hide();
					$("#updatebutton").show();
				}
			});
		});	
	</script>
</body>
</html>