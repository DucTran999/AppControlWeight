<?php
include "connect.php";
	$username = $_POST['username'];
	$password = $_POST['password'];
	$newpass =  $_POST['newpass'];

	$sql = "SELECT id FROM users WHERE username ='$username'AND password = '".md5($password)."'";
	$query = mysqli_query($conn, $sql);
	$row = mysqli_fetch_array($query);
	if(empty($row['id'])){
		echo "Không thành công";
	}
	else{
		$sql_update = "UPDATE users SET password = '".md5($newpass)."' WHERE id = '".$row['id']."'" ;
		$query_now = mysqli_query($conn, $sql_update);
		echo "Thành công";
	}
?>