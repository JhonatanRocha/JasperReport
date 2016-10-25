<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Report</title>
</head>
<body>
	<h1>Entre com um intervalo de data no formato dd/MM/YYYY</h1>
	<br/>
	<form action="gastos" method="POST">
		<label>Initial Date: </label>
		<input type="text" name="data_ini"/>
		<br/>
		<label>End Date: </label>
		<input type="text" name="data_fim"/>
		<br/>
		<input type="submit" value="Generate PDF">
	</form>
</body>
</html>