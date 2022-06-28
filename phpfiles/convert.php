<?php
include 'connect.php';



if (isset($_GET['name']) && isset($_GET['date']) && isset($_GET['price'])) {

    $patientName  =$_GET['name'];
    $date = $_GET['date'];
    $price = $_GET['price'];

    
    $query_cons = "INSERT INTO consultations (patientID, date, payment_value) VALUES ('$patientName', '$date','$price');";
    if ($r = $link->query($query_cons)) {
         $response["error"] = "false";

    } else {
         $response["error"] = "true";
    }

    $json = json_encode($response);
    print_r($json);
}
?>