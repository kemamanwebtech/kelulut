<?php

if ($_SERVER['REQUEST_METHOD']=='POST'){
 
 	$id = $_POST['id'];
 	$name = $_POST['name'];
 	$comment = $_POST['comment'];


 	require_once('db_connect.php');
 
	$sql = "INSERT INTO kelulut_comment (id, name, comment) VALUES ('$id', '$name', '$comment')";


	if (mysqli_query($con, $sql)) {
		echo "Successfully Uploaded.";
	} else {
	 echo "Error.";
	}
	 
	mysqli_close($con);

} else {
	 echo "Error.";
}

?>