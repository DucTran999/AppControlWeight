-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 11, 2021 at 03:50 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `appsuckhoe`
--

-- --------------------------------------------------------

--
-- Table structure for table `chitietdonhang`
--

CREATE TABLE `chitietdonhang` (
  `id` int(11) NOT NULL,
  `madonhang` int(11) NOT NULL,
  `masanpham` int(11) NOT NULL,
  `tensanpham` varchar(10000) NOT NULL,
  `giasanpham` int(11) NOT NULL,
  `soluongsanpham` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `chitietdonhang`
--

INSERT INTO `chitietdonhang` (`id`, `madonhang`, `masanpham`, `tensanpham`, `giasanpham`, `soluongsanpham`) VALUES
(9, 10, 1, 'Whey Gold Standard 5lbs', 3000000, 2),
(10, 10, 2, 'Rule 1 Proteins 5lbs ', 2580000, 2),
(11, 10, 3, 'ISO HD 4.9lbs', 1150000, 1),
(12, 11, 1, 'Whey Gold Standard 5lbs', 3000000, 2),
(13, 11, 2, 'Rule 1 Proteins 5lbs ', 2580000, 2),
(14, 11, 3, 'ISO HD 4.9lbs', 1150000, 1),
(15, 12, 2, 'Rule 1 Proteins 5lbs ', 1290000, 1),
(16, 13, 2, 'Rule 1 Proteins 5lbs ', 1290000, 1),
(17, 14, 1, 'Whey Gold Standard 5lbs', 1500000, 1),
(18, 15, 2, 'Rule 1 Proteins 5lbs ', 1290000, 1),
(19, 15, 8, 'Muscle Mass Gainer 12lbs ', 1550000, 1),
(20, 15, 14, 'Best BCAA 30 lần dùng', 690000, 1),
(21, 16, 2, 'Rule 1 Proteins 5lbs ', 14190000, 11),
(22, 16, 3, 'ISO HD 4.9lbs', 1150000, 1),
(23, 17, 1, 'Whey Gold Standard 5lbs', 1500000, 1),
(24, 18, 2, 'Rule 1 Proteins 5lbs ', 10320000, 8),
(25, 18, 1, 'Whey Gold Standard 5lbs', 7500000, 5),
(26, 19, 8, 'Muscle Mass Gainer 12lbs ', 6200000, 4),
(27, 20, 7, 'Serious Mass 12lbs', 9030000, 7),
(28, 21, 13, 'Best BCAA 60 Lần dùng', 2250000, 3),
(29, 21, 15, 'Rule 1 EAAs Energy 30 servings', 1890000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `donhang`
--

CREATE TABLE `donhang` (
  `id` int(11) NOT NULL,
  `tenkhachhang` varchar(255) NOT NULL,
  `sodienthoai` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `ngaydat` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `donhang`
--

INSERT INTO `donhang` (`id`, `tenkhachhang`, `sodienthoai`, `email`, `ngaydat`) VALUES
(6, 'Duc tran', 123456789, 'trananhduc@gmail.com', '08/06/2021'),
(10, 'Duc tran', 123456789, 'tranduc@gmail.com', '08/06/2021'),
(11, 'Duc tran', 123456789, 'tranduc@gmail.com', '08/06/2021'),
(12, 'anhduc', 123456789, 'ductran9x@gmail.com', '08/06/2021'),
(13, 'anhduc', 123456789, 'ductran9x@gmail.com', '08/06/2021'),
(14, 'anhduc', 1234567890, 'trananhduc@gmail.com', '08/06/2021'),
(15, 'Anh duc', 123456789, 'ductran@gmail.com', '09/06/2021'),
(16, 'AnhDuc', 123456789, 'ad@gmail.com', '09/06/2021'),
(17, 'anhduc', 123456789, 'abc@gmail.com', '09/06/2021'),
(19, 'Anh Duc', 123456789, 'ductran9x@gmail.com', '09/06/2021'),
(20, 'anhduc', 1234, 'abc', '09/06/2021'),
(21, 'Batu', 123456789, 'batutocon@gmail.com', '10/06/2021');

-- --------------------------------------------------------

--
-- Table structure for table `loaisanpham`
--

CREATE TABLE `loaisanpham` (
  `id` int(11) NOT NULL,
  `tenloaisanpham` varchar(200) NOT NULL,
  `hinhanhloaisanpham` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `loaisanpham`
--

INSERT INTO `loaisanpham` (`id`, `tenloaisanpham`, `hinhanhloaisanpham`) VALUES
(1, 'Whey', 'https://wheyshop.vn/wp-content/uploads/2020/04/Whey-Gold-Standard-5lbs-23kg-1.jpg'),
(2, 'Mass', 'https://wheyshop.vn/wp-content/uploads/2016/10/2-1.jpg'),
(3, 'Bcaa', 'https://wheyshop.vn/wp-content/uploads/2017/03/Mutant-BCAA-97-90-L%E1%BA%A7n-D%C3%B9ng.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE `sanpham` (
  `id` int(11) NOT NULL,
  `tensanpham` varchar(200) NOT NULL,
  `giasanpham` int(11) NOT NULL,
  `hinhanhsanpham` varchar(200) NOT NULL,
  `motasanpham` varchar(10000) NOT NULL,
  `idsanpham` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sanpham`
--

INSERT INTO `sanpham` (`id`, `tensanpham`, `giasanpham`, `hinhanhsanpham`, `motasanpham`, `idsanpham`) VALUES
(1, 'Whey Gold Standard 5lbs', 1500000, 'https://wheyshop.vn/wp-content/uploads/2020/04/Whey-Gold-Standard-5lbs-23kg-1.jpg', 'Whey Gold Standard 100% là sản phẩm Whey tăng cơ với hơn 30 năm uy tín thương hiệu hàng đầu thế giới,cam kết chính hãng, chất lượng và giá rẻ nhất Hà Nội, TpHCM\r\nThương hiệu: Optimum Nutrition\r\nĐịnh lượng: 5lbs\r\nSKU: 748927028669', 1),
(2, 'Rule 1 Proteins 5lbs ', 1290000, 'https://wheyshop.vn/wp-content/uploads/2016/12/Whey-Rule-1-Protein-5lbs-1.jpg', 'Whey Rule 1 Protein là sản phẩm phát triển cơ bắp cung cấp 100% Whey Isolate và Hydrolyzed hấp thu nhanh. Whey Rule 1 protein nhập khẩu chính hãng, cam kết chất lượng, giá rẻ nhất tại Hà Nội & Tp.HCM.\r\nThương hiệu: Rule 1\r\nĐịnh lượng: 2.2kg\r\nSKU: 858925004050', 1),
(3, 'ISO HD 4.9lbs', 1150000, 'https://wheyshop.vn/wp-content/uploads/2019/03/BPI-Whey-ISO-HD-5lbs-23kg-1.jpg', 'Whey BPI ISO HD là sản phẩm 100% Whey Isolate hỗ trợ phục hồi, phát triển cơ bắp hiệu quả. Cam kết nhập khẩu chính hãng uy tín và giá rẻ nhất tại Hà Nội, TpHCM\r\nThương hiệu: BPI Sport\r\nĐịnh lượng: 5lbs\r\nSKU: 810516031203', 1),
(4, 'Iso 100 5lbs ', 1890000, 'https://wheyshop.vn/wp-content/uploads/2016/12/Whey-Dymatize-Iso-100-5lbs-227kg-1.jpg', 'Dymatize Whey ISO 100 là thực phẩm bổ sung Whey Hydrolyzed hỗ trợ cơ bắp hiệu quả nhất, cam kết chính hãng, uy tín và giá rẻ tại Hà Nội, TpHCM.\r\nThương hiệu: Dymatize\r\nĐịnh lượng: 5lbs\r\nSKU: 705016353187', 1),
(5, '100% Whey Gold Standard 10lb', 3000000, 'https://wheyshop.vn/wp-content/uploads/2020/04/Whey-Gold-Standard-10lbs-454kg-1.jpg', 'Whey Gold Standard 10lbs sản phẩm sữa tăng cơ uy tín bán chạy hơn 30 năm, cam kết chất lượng, nhập khẩu chính hãng giá rẻ nhất Hà Nội, TPHCM.\r\nThương hiệu: Optimum Nutrition\r\nĐịnh lượng: 10lbs\r\nSKU: 748927028713\r\n', 1),
(6, 'Rule 1 Protein 10lbs', 2400000, 'https://wheyshop.vn/wp-content/uploads/2016/12/Whey-Rule-1-Protein-10lbs-1.jpg', 'Whey Rule 1 Proteins là sản phẩm tăng cơ nhanh TOP 1, bán chạy hàng đầu năm 2020 bởi chất lượng vượt trội, chính hãng và giá rẻ nhất tại Hà Nội, TpHCM…\r\nThương hiệu: Rule 1\r\nĐịnh lượng: 10lbs\r\nSKU: 858925004807', 1),
(7, 'Serious Mass 12lbs', 1290000, 'https://wheyshop.vn/wp-content/uploads/2016/10/ON-Serious-Mass-12lbs-54kg.jpg', 'Serious Mass 12lbs (5.4kg) là sữa tăng cân nhanh, chất lượng nhất trên thị trường, cam kết nhập khẩu chính hãng, uy tín, giá rẻ nhất Hà Nội TpHCM\r\nThương hiệu: Optimum Nutrition\r\nĐịnh lượng: 12lbs\r\nSKU: 748927028874', 2),
(8, 'Muscle Mass Gainer 12lbs ', 1550000, 'https://wheyshop.vn/wp-content/uploads/2016/10/2-1.jpg', 'Muscle Mass Gainer là sản phẩm hỗ trợ tăng cân tăng cơ hàng đầu hiện nay. Muscle Mass Gainer luôn được ưa chuộng bởi tăng cân nhanh, chất lượng , mùi vị vô cùng tuyệt vời.\r\nThương hiệu: Labrada\r\nĐịnh lượng: 12lbs\r\nSKU: 710779570023', 2),
(9, 'Serious Mass 6lbs', 850000, 'https://wheyshop.vn/wp-content/uploads/2016/10/ON-Serious-Mass-6lbs-27kg.jpg', 'Serious Mass 6lbs (2.72kg) là sữa tăng cân nhanh, chất lượng nhất trên thị trường, cam kết nhập khẩu chính hãng, uy tín, giá rẻ nhất Hà Nội TpHCM.\r\nThương hiệu: Optimum Nutrition\r\nĐịnh lượng: 6lbs\r\nSKU: 748927022995', 2),
(10, 'Super Mass Gainer 12lbs', 1290000, 'https://wheyshop.vn/wp-content/uploads/2016/10/Dymatize-Super-Mass-Gainer-12lbs-54kg.jpg', 'Dymatize Super Mass Gainer là sản phẩm tăng cân nhanh, giá rẻ nhất hiện nay. Super Mass được nhập khẩu chính hãng, chất lượng và cam kết giá rẻ nhất Việt Nam\r\nThương hiệu: Dymatize\r\nĐịnh lượng: 12lbs\r\nSKU: 705016331529', 2),
(11, 'Elite Labs Mass Muscle Gainer 5lbs ', 1900000, 'https://wheyshop.vn/wp-content/uploads/2017/02/Elitelabs-Mass-Muscle-Gainer-5lbs-23kg.jpg', 'Elite Labs Mass Muscle là sản phẩm hỗ trợ tăng cân tăng cơ nạc hàng đầu hiện nay. Elite Labs Mass Muscle nhập khẩu chính hãng, cam kết chất lượng, giá rẻ nhất tại Hà Nội \r\nThương hiệu: Elite Labs\r\nĐịnh lượng: 5lbs\r\nSKU: 606280842530', 2),
(12, 'MUTANT MASS XXXTREME 2500 22LBS', 2500000, 'https://wheyshop.vn/wp-content/uploads/2019/02/MUTANT-MASS-XXXTREME-2500-22LBS-1.jpg', 'https://wheyshop.vn/wp-content/uploads/2019/02/MUTANT-MASS-XXXTREME-2500-22LBS-1.jpg', 2),
(13, 'Best BCAA 60 Lần dùng', 750000, 'https://wheyshop.vn/wp-content/uploads/2016/10/BPI-Best-Bcaa-60-l%E1%BA%A7n-d%C3%B9ng.jpg', 'Best BCAA 60 servings là sản phẩm hỗ trợ phục hồi cơ bắp của hãng sản xuất BPI, kết hợp thành phần CLA chuyển hóa mỡ thừa tự nhiên. Best BCAA 60 servings chính hãng, cam kết chất lượng, giá rẻ nhất tại Hà Nội & Tp.HCM.\r\nThương hiệu: BPI Sport\r\nĐịnh lượng: 600 Gram\r\nSKU: 810516031371', 3),
(14, 'Best BCAA 30 lần dùng', 690000, 'https://wheyshop.vn/wp-content/uploads/2016/10/BPI-Best-Bcaa-30-l%E1%BA%A7n-d%C3%B9ng.jpg', 'Best BCAA hỗ trợ phục hồi và phát triển cơ bắp , chống dị hóa cơ bắp trong tập luyện, kết hợp chuyển hóa mỡ thừa thành năng lượng. Best BCAA  nhập khẩu chính hãng, cam kết chất lượng, giá rẻ nhất tại Hà Nội & Tp.HCM.\r\nThương hiệu: BPI Sport\r\nĐịnh lượng: 300 Gram\r\nSKU: 811213024857', 3),
(15, 'Rule 1 EAAs Energy 30 servings', 1890000, 'https://wheyshop.vn/wp-content/uploads/2020/07/Rule-1-EAAs-energy-30-servings-gia-re-ha-noi-tphcm.jpg', 'Xtend BCAA 90 servings hỗ trợ phục hồi và phát triển cơ hiệu quả nhất hiện nay . Xtend BCAA là sản phẩm được đánh giá cao về cả chất lượng lần mùi vị ngon\r\nThương hiệu: Scivation\r\nĐịnh lượng: 90 Serving\r\nSKU: 181030000168', 3),
(16, 'Mutant BCAA 9.7 30 lần dùng', 550000, 'https://wheyshop.vn/wp-content/uploads/2018/05/Mutant-BCAA-97-30-L%E1%BA%A7n-D%C3%B9ng.jpg', 'Mutant BCAA 9.7 30 lần dùng là sản phẩm chống dị hóa cơ bắp, phục hồi cơ bắp hàng đầu hiện này trên thị trường. Mutant BCAA 9.7 nhập khẩu chính hãng, cam kết chất lượng, giá rẻ nhất tại Hà Nội & Tp.HCM.\r\nThương hiệu: Mutant\r\nĐịnh lượng: 30 Serving\r\nSKU: 627933022703', 3),
(17, 'Rule 1 EAAs 30 servings', 690000, 'https://wheyshop.vn/wp-content/uploads/2019/11/Rule-1-EAAs-30-servings.jpg', 'Rule 1 EAAs 30 servings cung cấp các amino acid thiết yếu, hỗ trợ phục hồi cơ bắp, tăng tổng hợp protein hiệu quả, giá rẻ chính hãng. Rule 1 EAAs 30 servings nhập khẩu chính hãng, cam kết chất lượng, giá rẻ nhất tại Hà Nội & Tp.HCM.\r\nThương hiệu: Rule 1\r\nĐịnh lượng: 30 Serving\r\nSKU: 837234108413', 3),
(18, 'Biotech BCAA Zero 77 servings', 1050000, 'https://wheyshop.vn/wp-content/uploads/2019/07/Biotech-BCAA-Zero-77-servings.jpg', 'Biotech BCAA Zero bổ sung nguồn BCAA cao cấp hỗ trợ xây dựng cơ bắp hiệu quả. Biotech BCAA Zero nhập khẩu chính hãng, cam kết chất lượng, giá rẻ nhất tại Hà Nội & Tp.HCM.\r\nThương hiệu: Biotech\r\nĐịnh lượng: 700 Gram\r\nSKU: 5999076223473', 3);

-- --------------------------------------------------------

--
-- Table structure for table `thucpham`
--

CREATE TABLE `thucpham` (
  `id` int(11) NOT NULL,
  `tenthucpham` varchar(255) NOT NULL,
  `protein` varchar(11) NOT NULL,
  `caloris` varchar(11) NOT NULL,
  `hinhanhthucpham` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `thucpham`
--

INSERT INTO `thucpham` (`id`, `tenthucpham`, `protein`, `caloris`, `hinhanhthucpham`) VALUES
(1, 'Trứng', '13', '155', 'https://monngonbinhdinh.vn/Images/images/Tr%E1%BB%A9ng%20g%C3%A0.jpg'),
(2, 'Sữa', '10', '42', 'https://cdn.tgdd.vn/2020/12/content/2-800x500-15.jpg'),
(3, 'Thịt gà', '27', '100', 'http://thitngonnhapkhau.vn/plugins/responsive_filemanager/source/h%E1%BA%A3i%20s%E1%BA%A3n/g%C3%A0.png'),
(4, 'Thịt bò', '100', '250', 'https://cdn.tgdd.vn/2021/01/content/bo%CC%80vai-800x500.jpg'),
(5, 'Thịt lợn', '27', '242', 'https://photo-cms-baonghean.zadn.vn/w607/Uploaded/2021/tuqzxgazsnzm/2018_11_08/143638-1.jpg'),
(6, 'Rau chân vịt', '11', '23', 'https://s.meta.com.vn/img/thumb.ashx/Data/image/2020/07/06/rau-chan-vit-3.jpg'),
(7, 'Thăn cá ngừ', '29', '129', 'https://y5kbp0ifnvobj.vcdn.cloud/uploads/filecloud/2020/September/18/6591-885851600416686-1600416686--400x400.png');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `fullname` text NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` text NOT NULL,
  `email` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `fullname`, `username`, `password`, `email`) VALUES
(5, 'Đức Trần', 'anhduc', 'e10adc3949ba59abbe56e057f20f883e', 'ductran9x@gmail.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `loaisanpham`
--
ALTER TABLE `loaisanpham`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `thucpham`
--
ALTER TABLE `thucpham`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT for table `donhang`
--
ALTER TABLE `donhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `loaisanpham`
--
ALTER TABLE `loaisanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `thucpham`
--
ALTER TABLE `thucpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
