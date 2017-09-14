<?php
 
if ($_SERVER['REQUEST_METHOD']=='POST') {
	
	$name = $_POST['name'];
	$email = $_POST['email'];
	$passwd = $_POST['passwd'];
 
	require_once('db_connect.php');
 
 	$sql = "INSERT INTO kelulut_user (name, email, passwd) VALUES ('$name','$email','$passwd')";
 
 
	if (mysqli_query($con, $sql)) {
	 	echo "Successfully Registered";
	} else {
	 	echo "Error. Could not register.";
	} 
}

?>