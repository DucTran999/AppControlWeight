<?php
if(isset($_POST['fullname']) && isset($_POST['email']) && isset($_POST['username']) && isset($_POST['password'])){
	
	require_once "connect.php";
	require_once "validate.php";
	
	$fullname = validate($_POST['fullname']);
	$email = validate($_POST['email']);
	$username = validate($_POST['username']);
	$password = validate($_POST['password']);
	$sql = "INSERT INTO users VALUES ('','$fullname','$username','".md5($password)."','$email')";
	
	if(!$conn->query($sql)){
		echo "failure";
	}
	else{
		echo "success";
	}
}
?>