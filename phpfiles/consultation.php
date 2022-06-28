<?php
include 'connect.php';



if (isset($_GET['name'])) {
    $username = $_GET['name'];


$q_cons = "select * from consultations where patientID='$username'";
$result = mysqli_query($link, $q_cons);

// check for empty result
if ($result = mysqli_query($link, $q_cons)) {

    if ($result->num_rows > 0) {

        $response["messages"] = array();
        $response["error"] = "false";
        while ($row = $result->fetch_assoc()) {

            $consultations = array();

            $consultations["date"] = $row["date"];
            $consultations["payment_value"] = $row["payment_value"];


            array_push($response["messages"], $consultations);
        }
    }else{

        $response["messages"] = "There are no consultation";
        $response["error"] = "false";
    }
}
else{

    $response["messages"] = "Something Went Wrong";
    $response["error"] = "true";
}
// echoing JSON response
echo json_encode($response);
}
?>