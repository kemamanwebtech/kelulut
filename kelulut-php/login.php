<?php
 
if ($_SERVER['REQUEST_METHOD']=='POST') {

	$email = $_POST['email'];
	$passwd = $_POST['passwd'];
 
	require_once('db_connect.php');
 
 	$sql = "SELECT name, email, passwd FROM kelulut_user WHERE email = ? AND passwd = ? LIMIT 1";
 	
 	$stmt = $con->prepare($sql);


 	$stmt->bind_param("ss", $email, $passwd);

 	$result = $stmt->execute();
    $stmt->close();

    if ($result) {
	 	echo "Successfully login";
	} else {
	 	echo "Error. Could not register.";
	} 
}

?>