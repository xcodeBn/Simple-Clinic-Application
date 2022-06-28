<?php
include 'connect.php';


if (isset($_GET['date'])&& isset($_GET['name']) && isset($_GET['symptoms']) && isset($_GET['oldDate']) ) {

    $username = $_GET['name'];
    $newDateChosen = $_GET['date'];
    $symptoms = $_GET['symptoms'];
    $oldate = $_GET['oldDate'];

    $q = "UPDATE appointements
    SET date =  '$newDateChosen'
    WHERE  patientuser = '$username' and date = '$oldate' and symptomsDescription= '$symptoms'";
    if ($r = $link->query($q)) {
         $response["error"] = "false";
    } else {
         $response["error"] = "true";
    }

    $json = json_encode($response);
    print_r($json);
}
?>
