<?php
	include "connect.php";
	$query = "SELECT * FROM thucpham";
	$data = mysqli_query($conn, $query);
	$mangloaisp = array();

	while ($row = mysqli_fetch_assoc($data)) {
		$loaisp = new Loaisp(
			$row['id'],
			$row['tenthucpham'],
			$row['protein'],
			$row['caloris'],
			$row['hinhanhthucpham']
		);
		array_push($mangloaisp,$loaisp);
		}
	echo json_encode($mangloaisp);
	
	class Loaisp{
		public $id,$tentp,$protein,$caloris,$hinhanhtp;
		function __construct($id,$tentp,$protein,$caloris,$hinhanhtp){
			$this->id = $id;
			$this->tentp = $tentp;
			$this->protein = $protein;
			$this->caloris = $caloris;
			$this->hinhanhtp = $hinhanhtp;
		}
	}
?>