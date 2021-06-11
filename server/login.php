<?php
include "connect.php";
	$username = $_POST['username'];
	$password = $_POST['password'];

	$sql = "SELECT * FROM users WHERE username ='$username'AND password = '".md5($password)."'";
	$result = mysqli_query($conn, $sql);
	
	$thongtin = array();
	while ($row = mysqli_fetch_assoc($result)) {
		$user = new Users(
			$row['id'],
			$row['fullname'],
			$row['username'],
			$row['password'],
			$row['email']
		);
		array_push($thongtin,$user);
		}
		if(empty($thongtin)){
			echo "Empty";
		}
		else{
			echo json_encode($thongtin);
		}
	
	
	class Users{
		public $id,$ten,$username,$password,$email;
		function __construct($id,$ten,$username,$password,$email){
			$this->id = $id;
			$this->ten = $ten;
			$this->username = $username;
			$this->password = $password;
			$this->email = $email;
		}
	}
?>