<?php
 
if ($_SERVER['REQUEST_METHOD']=='POST') {

	define('HOST','144.217.93.255');
 	define('USER','kwt');
 	define('PASS','Enter_123');
 	define('DB','db_kelulut');

	$return_arr = array();

	$pdo = new PDO('mysql:host='.HOST.';dbname='.DB,USER,PASS); 

	$array = $pdo->query("SELECT id, location, image_desc FROM kelulut_map ")->fetchAll(PDO::FETCH_ASSOC);
	
	echo json_encode($array);
}

?>