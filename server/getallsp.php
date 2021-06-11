<?php
	include "connect.php";
	$mangsanpham = array();
	$query = "SELECT * FROM sanpham";
	$data = mysqli_query($conn, $query);
	
	while ($row = mysqli_fetch_assoc($data)) {
		$loaisp = new Sanpham(
			$row['id'],
			$row['tensanpham'],
			$row['giasanpham'],
			$row['hinhanhsanpham'],
			$row['motasanpham'],
			$row['idsanpham']);
		
	array_push($mangsanpham,$loaisp);
		}
	echo json_encode($mangsanpham);
	
	class Sanpham{
		public $id, $tensp, $giasp, $hinhanhsp, $motasp, $idsanpham;
		function __construct($id, $tensp,$giasp,$hinhanhsp,$motasp,$idsanpham){
			$this->id = $id;
			$this->tensp = $tensp;
			$this->giasp = $giasp;
			$this->hinhanhsp = $hinhanhsp;
			$this->motasp = $motasp;
			$this->idsanpham = $idsanpham;
		}
	}
?>