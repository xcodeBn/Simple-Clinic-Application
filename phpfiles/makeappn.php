<?php
include 'connect.php';



if (isset($_GET['name']) && isset($_GET['date']) && isset($_GET['symptoms']) && isset($_GET['firstVis'])) {
    $username = $_GET['name'];
    $date = $_GET['date'];
    $symptomsDescription = $_GET['symptoms'];
    $isFirstVisit = $_GET['firstVis'];


    $q_makeAppn = "INSERT INTO appointements values('$username',' $date',' $symptomsDescription','$isFirstVisit')";

    $result = mysqli_query($link, $q_makeAppn);
    // check for empty result
    if ($result = mysqli_query($link, $q_makeAppn)) {

        $response["error"] = "Appoinment Done";

    }else{

        $response["error"] = "true";

    }
    // echoing JSON response
    echo json_encode($response);
}
