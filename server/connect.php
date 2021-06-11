<?php
	$host = "localhost";
	$username = "root";
	$password = "";
	$database = "appsuckhoe";

	$conn = mysqli_connect($host, $username, $password, $database);
	mysqli_query($conn, "SETNAME 'utf8");
?>