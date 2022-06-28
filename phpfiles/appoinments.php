<?php
include 'connect.php';


if (isset($_GET['name'])) {
    $username = $_GET['name'];



$q_appn = "select * from appointements where patientuser='$username'";
$result = mysqli_query($link, $q_appn);


// check for empty result
if ($result = mysqli_query($link, $q_appn)) {

    if ($result->num_rows > 0) {

        $response["messages"] = array();
        $response["error"] = "false";
        while ($row = $result->fetch_assoc()) {

            $appoinments = array();

            $appoinments["date"] = $row["date"];
            $appoinments["symptomsDescription"] = $row["symptomsDescription"];


            array_push($response["messages"], $appoinments);
        }
    }else{
        $response["messages"] = "You have no Appoinments to display";
        $response["error"] = "false";
    }
}else{
    $response["messages"] = "Something Went Wrong";
    $response["error"] = "true";
}
// echoing JSON response

echo json_encode($response);
}
?>