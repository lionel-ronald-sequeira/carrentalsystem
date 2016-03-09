<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Car Rental System</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				 <li class="active" id="carinfolink"><a href="${ctx}/addcarinfo" id="carhref">Car information</a></li>
       			 <li id="customerinfolink"><a href="${ctx}/addcustomerinfo" id="custhref">Customer information</a></li>
       			 <li id="rentalinfolink"><a href="${ctx}/viewrentalinfo">Rental information</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Settings <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="${ctx}/signout">SignOut</a></li>
					</ul>
				</li>
			</ul>
		</div>
		</div>
		<script type="text/javascript">
		 $("#carhref").click(function (){
			 $( "li.active" ).find( "li" ).removeClass("active");
			 $("#carinfolink").attr("class","active");
		 });
		 $("#customerhref").click(function (){
			 $( "li.active" ).find( "li" ).removeClass("active");
			 $("#customerinfolink").attr("class","active");
		 });
		</script>
</nav>

