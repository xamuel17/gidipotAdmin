<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang ="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Add new Admin</title>

  <!-- Custom fonts for this template-->
  <link href="static/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="static/css/sb-admin-2.css" rel="stylesheet">
<link href="css/registration.css" rel="stylesheet">
<script>
function validateForm() {
  var uname = document.forms["myForm"]["username"].value;
  var  fname = document.forms["myForm"]["firstname"].value;
  var lname = document.forms["myForm"]["lastname"].value;
  var ph = document.forms["myForm"]["phoneno"].value;
  var role = document.forms["myForm"]["role"].value;
  var pass1 = document.forms["myForm"]["pass1"].value;
  var pass2 = document.forms["myForm"]["pass2"].value;
  if (uname == ""|| fname==""| lname =="" | ph =="" | role =="" | pass1 == ""| pass2 =="") {
    alert("Fields Must Not Be Empty");
    return false;
  }
}
</script>



  <script src="static/js/postrequest.js"></script>

</head>
<body  >

<h1>${head} </h1>
	
 

<div style="float:right; width:70%; margin-top:-1%;">
         <div class="registration-page">
  <div class="form">
    <form class="register-form" action="create" method="post" name="myForm" onsubmit="return validateForm()">
      
       <c:if test="${not empty message}">


<div class="alert alert-success" role="alert">
  <h4 class="alert-heading">Well done!</h4>
  <p>Registration  ${message.responseMessage}!</p>
  <hr>
  <p class="mb-0"></p>
</div>


</c:if>
      
      
      <input type="text" placeholder="Username" name="username" class="form-control"/>
        <input type="text" placeholder="FirstName" name="firstname" class="form-control"/>
          <input type="text" placeholder="LastName" name="lastname" class="form-control"/>
             <input type="text" placeholder="Phone No." name= "phoneno" class="form-control"/>
             <input type="text" placeholder="Role" name="role" class="form-control"/>
      <input type="password" placeholder="Password" name="pass1" class="form-control"/>
      <input type="password" placeholder="Re-type Password" name="pass2" class="form-control"/>
   
   
   
    <h2 id="demo">${message.responseMessage}!</h2>

      
      <button  type="submit"  onclick="myFunction()" ><b>create</b></button>
      
      
      
 

<c:if test="${not empty message}">

<div class="container">
 <div  id="mes">
    <strong >Registration  ${message.responseMessage}!</strong> 
    </div>
</div>

</c:if>
 
     
    </form>
   
       <script>
function myFunction() {
 var  t = document.getElementById("demo").innerHTML;
 alert(t);
}
</script>
    
    <div id="getResultDiv" style="padding:20px 10px 20px 50px">
    <ul class="list-group"></ul>
    </div>
<!-- Result Container  -->
<div id="resultContainer" style="display: none;">
 <hr/>
 <h4 style="color: green;">JSON Response From Server</h4>
  <pre style="color: green;">
    <code></code>
   </pre>
</div>
  </div>
</div> 
  </div>
            
           









<%@ include file = "Ssidebar.jsp" %>
 
            
  <!-- Bootstrap core JavaScript-->
  <script src="static/vendor/jquery/jquery.min.js"></script>
  <script src="static/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="static/vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="static/js/sb-admin-2.min.js"></script>

  <!-- Page level plugins -->
  <script src="static/vendor/chart.js/Chart.min.js"></script>

  <!-- Page level custom scripts -->
  <script src="static/js/demo/chart-area-demo.js"></script>
  <script src="static/js/demo/chart-pie-demo.js"></script>
</body>
</html>