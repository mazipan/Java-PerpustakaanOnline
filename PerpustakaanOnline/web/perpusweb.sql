/*
SQLyog Enterprise - MySQL GUI v8.18 
MySQL - 5.6.16 : Database - perpusweb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`perpusweb` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `perpusweb`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `true_name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `admin` */

insert  into `admin`(`id`,`username`,`password`,`true_name`) values (1,'admin','F/PZtTgA4k5q/9o6Jzik6w==','Administrator'),(4,'admin2','70P3MxmGdITwG86tgI3g0g==','Administrator 2');

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `id_book` int(5) NOT NULL AUTO_INCREMENT,
  `id_cat` int(5) NOT NULL,
  `book_title` varchar(50) NOT NULL,
  `pengarang` varchar(20) NOT NULL,
  `thn_terbit` date NOT NULL,
  PRIMARY KEY (`id_book`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

/*Data for the table `book` */

insert  into `book`(`id_book`,`id_cat`,`book_title`,`pengarang`,`thn_terbit`) values (1,1,'Dasar Java Programmer','Siswoyo','2013-01-01'),(2,1,'Algoritma Pemrograman','Adri','2009-01-01'),(3,1,'Memancing','Broto','2010-02-02'),(4,1,'Filosofi Hidup','Syafi\'i','2014-02-02'),(5,2,'Hidup Sederhana','Anonim','2009-02-02'),(6,2,'Belum Ada Judul','Iwan Fals','2012-02-02'),(7,2,'Judul','pengarang23','2011-01-01'),(8,2,'test1','author','2014-01-01'),(9,1,'test','author','2013-01-01'),(10,1,'test','author','2013-01-01'),(11,2,'test','author','2014-01-01'),(12,1,'Memancing','whos','2014-01-01'),(13,2,'Judulnya aneh','pengarang','2013-01-01'),(14,2,'Apa ya judulnya','siapa ini','2012-01-01'),(15,1,'Buku yang bikin bingung','pengarangnya','2014-01-01'),(16,2,'test','test','2014-01-01');

/*Table structure for table `book_category` */

DROP TABLE IF EXISTS `book_category`;

CREATE TABLE `book_category` (
  `id_cat` int(5) NOT NULL AUTO_INCREMENT,
  `name_cat` varchar(50) NOT NULL,
  PRIMARY KEY (`id_cat`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `book_category` */

insert  into `book_category`(`id_cat`,`name_cat`) values (1,'Sains2'),(2,'Hobby');

/*Table structure for table `member` */

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `id_member` int(5) NOT NULL AUTO_INCREMENT,
  `nik` varchar(10) NOT NULL,
  `nama` varchar(20) NOT NULL,
  `alamat` varchar(50) DEFAULT NULL,
  `telp` varchar(15) DEFAULT NULL,
  `bagian` varchar(20) NOT NULL,
  `lokasi` varchar(20) NOT NULL,
  `ext` varchar(15) DEFAULT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id_member`),
  UNIQUE KEY `nik` (`nik`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `member` */

insert  into `member`(`id_member`,`nik`,`nama`,`alamat`,`telp`,`bagian`,`lokasi`,`ext`,`password`) values (1,'2009470156','IRFAN MAULANA','Cempaka Baru','085781238798','Programmer','Sunter',NULL,'oJfTtchpM9WC/4Oqpu7FZQ=='),(2,'2009470157','MAZIPAN','Kemayoran','085781238798','Programmer','Sunter',NULL,'oJfTtchpM9WC/4Oqpu7FZQ=='),(3,'2009','NAMA','alamat','telepon','bagian','lokasi','2121','oJfTtchpM9WC/4Oqpu7FZQ==');

/*Table structure for table `newsupdate` */

DROP TABLE IF EXISTS `newsupdate`;

CREATE TABLE `newsupdate` (
  `id_news` int(5) NOT NULL AUTO_INCREMENT,
  `news_name` varchar(50) NOT NULL,
  `news_time` datetime DEFAULT NULL,
  `news_place` varchar(50) DEFAULT NULL,
  `news_desc` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id_news`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `newsupdate` */

insert  into `newsupdate`(`id_news`,`news_name`,`news_time`,`news_place`,`news_desc`) values (1,'Activities 1','2014-07-13 15:19:51','Gedung Kesenian Miss Tji-tjih, Cempaka Putih, Jaka','Acara Pengajian, dll...'),(4,'newName','2014-07-17 16:27:54','newPlace','newDesc'),(5,'newName','2014-07-17 16:49:35','newPlace','newDesc'),(7,'test','2014-07-13 15:21:02','test','tess');

/*Table structure for table `peminjaman` */

DROP TABLE IF EXISTS `peminjaman`;

CREATE TABLE `peminjaman` (
  `id_pinjam` int(5) NOT NULL AUTO_INCREMENT,
  `id_member` int(5) NOT NULL,
  `tgl_pinjam` date NOT NULL,
  `tgl_kembali` date NOT NULL,
  `isKembali` tinyint(1) DEFAULT NULL,
  `id_book` int(5) NOT NULL,
  `isDenda` tinyint(1) DEFAULT NULL,
  `tgl_pengembalian` date DEFAULT NULL,
  PRIMARY KEY (`id_pinjam`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;

/*Data for the table `peminjaman` */

insert  into `peminjaman`(`id_pinjam`,`id_member`,`tgl_pinjam`,`tgl_kembali`,`isKembali`,`id_book`,`isDenda`,`tgl_pengembalian`) values (20,1,'2014-07-06','2014-07-20',1,7,1,'2014-08-09'),(21,1,'2014-08-09','2014-08-23',0,2,0,NULL),(22,1,'2014-08-09','2014-08-23',0,14,0,NULL),(23,1,'2014-08-09','2014-08-23',0,9,0,NULL),(24,1,'2014-08-09','2014-08-23',0,3,0,NULL),(25,1,'2014-08-09','2014-08-23',0,11,0,NULL),(26,1,'2014-08-09','2014-08-23',1,4,0,'2014-08-09'),(27,1,'2014-08-09','2014-08-23',0,6,0,NULL),(28,2,'2014-08-09','2014-08-23',0,15,0,NULL),(29,2,'2014-08-09','2014-08-23',0,1,0,NULL),(30,2,'2014-08-09','2014-08-23',0,4,0,NULL),(31,2,'2014-08-09','2014-08-23',0,5,0,NULL),(32,2,'2014-08-09','2014-08-23',0,7,0,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
