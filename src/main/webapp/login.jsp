<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
</head>
<body>
<div align="center">
	<h1>User Login</h1>
</div>
<form action="loginServlet" method="post">
	<table align="center">
	<tr>
		<td>User ID:</td>
		<td><input id="userid" type="text" name="txtName" onblur="validate()" /></td>
	</tr>
	<tr>
		<td>Password:</td>
		<td><input id="pwd" type="password" name="txtPwd" onblur="validate()" /></td>
	</tr>
	<tr>
		<td></td>
		<td><button type="submit" name="btn-submit" id="btn-submit" disabled="disabled">Login</button></td>
	</tr>
	</table>
</form>
</body>
<script type="text/javascript">
	function validate() {
		var userid = $('#userid').val();
		var password = $('#pwd').val();
		if(userid.length > 0 && password.length > 0) {
			$("#btn-submit").attr("disabled",false);
		}
	}

</script>
</html>