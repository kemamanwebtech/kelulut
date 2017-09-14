<?php
 
if ($_SERVER['REQUEST_METHOD']=='POST') {

	$id = $_POST['id'];
 
	require_once('db_connect.php');
 
 	$sql = "SELECT name, comment FROM kelulut_comment WHERE id = ? ";
 	
 	$stmt = $con->prepare($sql);

 	$stmt->bind_param("i", $id);

 	$result = $stmt->execute();

 	$stmt->store_result();

 	$stmt->bind_result($name, $comment);

 	$allComments = "";

 	while ($stmt->fetch()) {
        $allComments = $allComments . $name . "  :  " . $comment . "\n\n" ;
    } 


    if (!($allComments == "")) {
    	echo $allComments;
    } else {
    	echo "No comments found.";
    }

}

?>