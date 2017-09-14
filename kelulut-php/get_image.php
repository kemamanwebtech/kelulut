<?php
 
if ($_SERVER['REQUEST_METHOD']=='POST') {

	$id = $_POST['id'];
 
	require_once('db_connect.php');
 
 	$sql = "SELECT image_location FROM kelulut_map WHERE id =  ? LIMIT 1";
 	
 	$stmt = $con->prepare($sql);

 	$stmt->bind_param("s", $id);

 	$result = $stmt->execute();

 	$stmt->store_result();

 	$stmt->bind_result($image_location);

 	if ($stmt->fetch()) {
        echo $image_location;
    } else {
	 	echo "Error.";
	} 
}

?>