<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title> login screen </title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

<script>
var json = JSON.stringify(${loginResult});
var json2 = JSON.parse(json);
console.log(json)
var id2 = 'id Confirm';
//document.write(json2.id);
//document.write(id2);
</script>

<li> MR .<script>document.write(json2.id);</script> Thank you for Login </li>

</body>
</html>
