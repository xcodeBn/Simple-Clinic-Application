<?php
include 'connect.php';



if (isset($_GET['name']) && isset($_GET['date']) && isset($_GET['symptoms']) ) {

    $username = $_GET['name'];
    $date = $_GET['date'];
    $symptomsDescription = $_GET['symptoms'];
  

    $q = "DELETE FROM appointements WHERE patientuser = '$username' and date = '$date' and symptomsDescription= '$symptomsDescription'";

    if ($r = $link->query($q)) {

         $response["error"] = "false";
    } else {
        $response["error"] = "true";
    }
    $json = json_encode($response);
    print_r($json);

}
?>