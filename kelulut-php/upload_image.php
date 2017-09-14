<?php

if ($_SERVER['REQUEST_METHOD']=='POST'){
 
 	$image_data = $_POST['image_data'];
 	$email = $_POST['email'];
 	$image_desc = $_POST['image_desc'];
 	$location = $_POST['location'];

 	require_once('db_connect.php');
 
 	$sql =" SELECT id FROM kelulut_map ORDER BY id ASC";
 
	$res = mysqli_query($con, $sql);
	 
	$id = 0;
	 
	while($row = mysqli_fetch_array($res)){
		$id = $row['id'];
	}
	 

	$path = "uploaded_image/$id.png";
	$actualpath = "http://144.217.93.255/kelulut/$path";
	 
	$sql = "INSERT INTO kelulut_map (email, image_desc, image_location, location ) VALUES ('$email', '$image_desc', '$actualpath', '$location')";


	if (mysqli_query($con, $sql)) {
		file_put_contents($path, base64_decode($image_data));
		echo "Successfully Uploaded.";
	} else {
	 echo "Error.";
	}
	 
	mysqli_close($con);

} else {
	 echo "Error.";
}

?>