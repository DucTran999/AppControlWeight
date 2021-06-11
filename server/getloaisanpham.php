<?php
	include "connect.php";
	$query = "SELECT * FROM loaisanpham";
	$data = mysqli_query($conn, $query);
	$mangloaisp = array();

	while ($row = mysqli_fetch_assoc($data)) {
		$loaisp = new Loaisp(
			$row['id'],
			$row['tenloaisanpham'],
			$row['hinhanhloaisanpham']);
		array_push($mangloaisp,$loaisp);
		}
	echo json_encode($mangloaisp);
	
	class Loaisp{
		public $id,$tenloaisp,$hinhanhloaisp;
		function __construct($id, $tenloaisp,$hinhanhloaisp){
			$this->id = $id;
			$this->tenloaisp = $tenloaisp;
			$this->hinhanhloaisp = $hinhanhloaisp;
		}
	}
?>