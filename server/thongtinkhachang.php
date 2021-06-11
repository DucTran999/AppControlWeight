<?php
	include "connect.php";
	$tenkhachhang = $_POST['tenkhachhang'];
	$sodienthoai = $_POST['sodienthoai'];
	$email = $_POST['email'];
	$ngaydat = $_POST['ngaydat'];

	if(strlen($tenkhachhang) > 0 && strlen($email) && strlen($sodienthoai) > 0 && strlen($ngaydat) > 0){
		$query = "INSERT INTO donhang(id,tenkhachhang,sodienthoai,email,ngaydat) VALUES (null,'$tenkhachhang','$sodienthoai', '$email', '$ngaydat')";
		if(mysqli_query($conn, $query)){
			$iddonhang = $conn->insert_id;
			echo $iddonhang;
		}
		else{
			echo "Khong thanh cong";
		}
	}else{
		echo "Chua nhap day du thong tin";
	}
?>