<?php
 
if ($_SERVER['REQUEST_METHOD']=='POST') {

	$genus_name = $_POST['genus_name'];
 
	require_once('db_connect.php');
 
 	$sql = "SELECT about FROM info_kelulut WHERE genus_name LIKE  ? LIMIT 1";
 	
 	$stmt = $con->prepare($sql);

 	$param = "%" . $genus_name . "%";

 	$stmt->bind_param("s", $param);

 	$result = $stmt->execute();

 	$stmt->store_result();

 	$stmt->bind_result($about);

 	if ($stmt->fetch()) {
        echo $about;
    } else {
	 	echo "Error.";
	} 
}

?>