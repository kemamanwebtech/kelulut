<?php
 
if ($_SERVER['REQUEST_METHOD']=='POST') {

	$genus_name = $_POST['genus_name'];
 
	require_once('db_connect.php');
 
 	$sql = "SELECT species FROM species WHERE genus_name LIKE  ? ";
 	
 	$stmt = $con->prepare($sql);

 	$param = "%" . $genus_name . "%";

 	$stmt->bind_param("s", $param);

 	$result = $stmt->execute();

 	$stmt->store_result();

 	$stmt->bind_result($species);

 	$list_species = "";

 	while ($stmt->fetch()) {
        $list_species = $list_species . "\n" . $species;
    } 


    if (!($list_species == "")) {
    	echo $list_species;
    } else {
    	echo "Error.";
    }

}

?>