<?php
include 'connect.php';



if(isset($_POST)){
$currentDate = date("Y-m-d");
$q_todayappn = "select * from appointements where date = '$currentDate' ";
$result = mysqli_query($link, $q_todayappn);

// check for empty result
if ($result = mysqli_query($link, $q_todayappn)) {

    if ($result->num_rows > 0) {

        $response["messages"] = array();
        $response["error"] = "false";
        while ($row = $result->fetch_assoc()) {

          

            $todayAppn = array();
         
            $todayAppn["patient_name"] = $row["patientuser"];
            $todayAppn["date"] = $row["date"];
            $todayAppn["symptomsDescription"] = $row["symptomsDescription"];
            $todayAppn["isFirstVisit"] = $row["isFirstVisit"];
            array_push($response["messages"], $todayAppn);
        }
    } else {
        $response["error"] = "false";
        $response["messages"] = array();
       
     
    }
}else{
    $response["messages"] = "";
    $response["error"] = "true";
}
// echoing JSON response
echo json_encode($response);
}
?>