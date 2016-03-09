<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form"   prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Rental Information</title>
	<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/bootstrap-theme.css" />" rel="stylesheet">
	<script src="<c:url value="/resources/js/jquery-2.1.4.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.js" />"></script>
	<link href="<c:url value="/resources/css/datepicker.css" />" rel="stylesheet">
	<script src="<c:url value="/resources/js/bootstrap-datepicker.js" />"></script>
	</head>
<body>
<%@ include file="header.jsp" %>
<div class="container-fluid">
		<div class="panel panel-primary" style="margin-top:100px;">
			<div class="panel-heading">Add Rental Information</div>
			<div class="panel-body">
				<c:if test="${not empty msg}">
					<div class="row">
						<div class="col-lg-12">
							<div class="alert alert-success" role="alert">${msg}</div>
						</div>
					</div>
				</c:if>
				<form:form method="POST" modelAttribute="rentalinfo" id="rentalform"> 
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label>Start Date<span style="color:red">*</span></label>
								<div class="input-append date" data-date-format="mm-dd-yyyy">
									<form:input path="startDate" class="form-control" size="16" type="text" id="startdatepicker" placeholder="mm/dd/yyyy"/>
    							</div>
    						</div>
				    	</div>
				    	<div class="col-lg-3">
							<div class="form-group">
								<label>Return Date<span style="color:red">*</span></label>
								<div class="input-append date" data-date-format="mm-dd-yyyy">
									<form:input path="endDate" class="form-control" size="16" type="text" id="enddatepicker" placeholder="mm/dd/yyyy"/>
    							</div>
    						</div>
				    	</div>
				    	<div class="col-lg-3">
				    		<div class="form-group">
								<label>Car Type</label>
								<form:select path="carTypes" multiple="true" class="form-control" id="carTypes">
  									<form:option value="COMPACT">Compact</form:option>
  									<form:option value="MEDIUM">Medium</form:option>
 									<form:option value="LARGE">Large</form:option>
  									<form:option value="SUV">SUV</form:option>
  									<form:option value="TRUCK">Truck</form:option>
  									<form:option value="VAN">Van</form:option>
								</form:select>
				    		</div>
				    	</div>
				    	<div class="col-lg-3">
				    		<div class="form-group">
				    			<button type="submit" id="searchbutton" class="btn btn-primary" style="margin-top:25px;">Search Car Availability</button>
								<button type="submit" id="addbutton" class="btn btn-primary" style="margin-top:25px;">Add Rental Info</button>
								<button type="submit" id="updatebutton" class="btn btn-primary btn" style="margin-top:25px;">Update Amt due</button>
							</div>
						</div>
				    </div>
				    <div class="row">
				    	<div class="col-lg-3">
				    		<div class="form-group">
				    			<label>Customer<span style="color:red">*</span></label>
								<form:select class="form-control" path="customerId">
									<option>Select</option>
									<c:if test="${not empty customerList}">
										<c:forEach var="customerObj" items="${customerList}">
  											<option value="${customerObj.customerId}">${customerObj.firstName}${customerObj.lastName}</option>
  										</c:forEach>
  									</c:if>
  								</form:select>
				    		</div>
				 		</div>
				    	<div class="col-lg-3">
				    		<div class="form-group">
				    			<label>Rental Type<span style="color:red">*</span></label>
								<form:select class="form-control" path="rentalType" id="rentalTypes">
									<option>Select</option>
  									<option>daily</option>
  									<option>weekly</option>
  								</form:select>
				    		</div>
				 		</div>
				 		 <div class="col-lg-3" id="amountDueDiv">
				    		<div class="form-group">
				    			<label>Amount Due<span style="color:red">*</span></label>
								<form:input path="amountDue" class="form-control" size="16" type="text" id="amountdueval"/>
				    		</div>
				 		</div>
				   		<input type="hidden" id="ctxValue" value="${ctx}"/>
				   		<input type="hidden" id="rentalValue" value="${rentalmsg}"/>
				   		<form:input type="hidden" path="vehicleId" value="" id="rentalvehicleid" />
						<form:input type="hidden" path="rentalId" value="" id="rentalid" />
					</div>
				</form:form>
			</div>
		</div>
</div>
<div class="container-fluid" id="caravailableinfo">
	<div class="panel panel-primary" style="margin-top:20px;">
			<div class="panel-heading">Car Availability Information</div>
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
								<th>Action</th>
								<th>Vehicle Id</th>
								<th>Model</th>
								<th>Year</th>
								<th>Daily Rate</th>
								<th>Weekly Rate</th>
							<!--<th>Available</th> -->
								<th>Compact</th>
								<th>Medium</th>
								<th>Large</th>
								<th>SUV</th>
								<th>Truck</th>
								<th>Van</th>
							</tr>
							<c:forEach var="carObj" items="${carList}">
								<tr>
									<td><div class="radio">
										<label class="radio-inline">
  											<input type="radio"  id="carinforadio" name="radiovalues" value="${carObj.vehicleId}"/>
										</label>	
									</div></td>
									<td>${carObj.vehicleId}</td>
									<td>${carObj.model}</td>
									<td>${carObj.year}</td>
									<td>${carObj.dailyRate}</td>
									<td>${carObj.weeklyRate}</td>
									<!-- <td>${carObj.available}</td> -->
									<td>${carObj.compact}</td>
									<td>${carObj.medium}</td>
									<td>${carObj.large}</td>
									<td>${carObj.suv}</td>
									<td>${carObj.truck}</td>
									<td>${carObj.van}</td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
				</div>
			</div>
	</div>
</div>
<div class="container-fluid" id="rentalinfo">
	<div class="panel panel-primary" style="margin-top:20px;">
			<div class="panel-heading">Rental Information</div>
			<div class="panel-body">
				<c:if test="${empty rentalList}">
							<div class="row">
								<div class="col-lg-12">
									<div class="alert alert-danger" role="alert">Rental Information Not Available</div>
								</div>
							</div>
				</c:if>
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover">
				<c:if test="${not empty rentalList}">
							<tr>
								<th>Vehicle Id</th>
								<th>Model</th>
								<th>Customer Id</th>
								<th>Customer Name</th>
								<th>Start Date</th>
								<!-- <th>End Date</th> -->
								<th>Return Date</th>
								<th>Amount Due Paid</th>
								<th>Rental Type</th>
								<th>Rental Timing</th>
								<th>No Of Days</th>
								<th>No Of Weeks</th>
								<th>Action</a>
							</tr>
							<c:forEach var="rentalObj" items="${rentalList}">
								<tr>
									<td>${rentalObj.vehicleId}</td>
									<td>${rentalObj.vehicleModel}</td>
									<td>${rentalObj.customerId}</td>
									<td>${rentalObj.customerName}</td>
									<td>${rentalObj.startDate}</td>
								<!--<td>${rentalObj.endDate}</td> -->
									<td>${rentalObj.returnDate}</td>
									<td>${rentalObj.amountDue}</td>
									<td>${rentalObj.rentalType}</td>
									<td>${rentalObj.rentalTiming}</td>
									<td>${rentalObj.noOfDays}</td>
									<td>${rentalObj.noOfWeeks}</td>
									<td>
										<c:if test="${rentalObj.rentalTiming ne 'complete'}">
											<a href="#" class="returnTag" id="${rentalObj.rentalId}">Return</a>
										</c:if>
									</td>
								</tr>
						</c:forEach>
				</c:if>
				</table>
				</div>
			</div>
	</div>
</div>
<script type="text/javascript">
$('#amountDueDiv').hide();
$("#updatebutton").hide();

$('#startdatepicker').click(function(){
	$('#startdatepicker').datepicker('show');
});
$('#enddatepicker').click(function(){
	$('#enddatepicker').datepicker('show');
});
$('#searchbutton').click(function(){
	$("#rentalform").attr("action",$("#ctxValue").val().concat("/searchrentalinfo"));
});
$('#addbutton').click(function(){
	var radioButtonVal=$('input[name=radiovalues]:checked').val();
	$("#rentalvehicleid").attr("value",radioButtonVal);
	$("#rentalform").attr("action",$("#ctxValue").val().concat("/addrentalinfo"));
});
$('#updatebutton').click(function(){
	$("#rentalform").attr("action",$("#ctxValue").val().concat("/updaterentalinfo"));
});
if($('#rentalValue').val()==''){
	$("#caravailableinfo").show();
	$("#rentalinfo").hide();
}else{
	$("#caravailableinfo").hide();
	
	$("#rentalinfo").show();
}

$(".returnTag").click(function () {
    var attributeValue = $(this).attr("id");
    var data = 'rentalId='
		+ encodeURIComponent(attributeValue);

    $.ajax({
		url : $("#ctxValue").val().concat("/editrentalinfo"),
		data : data,
		type : "GET",
		success : function(response) {
			var values=response.split(",");
			$("#startdatepicker").val(values[0]);
			$("#startdatepicker").prop('disabled',true);
			
			$("#enddatepicker").val(values[1]);
			$("#enddatepicker").prop('disabled',true);
			
			$("#amountdueval").val(values[4]);
			$('#amountDueDiv').show();
			$("#updatebutton").show();
			$("#addbutton").hide();
			
			$('[name=customerId]').val( values[2]);
			$("#customerId").prop('disabled',true);
			
			$('[name=rentalType]').val( values[3]);
			$("#rentalTypes").prop('disabled',true);
			
			$("#carTypes").prop('disabled',true);
			$("#rentalid").val(values[5]);
			
		}
	});
});	

</script>
</body>
</html>