<?php
include 'connect.php';



if (isset($_GET['username']) && isset($_GET['password'])) {

    $username = $_GET['username'];
    $password = $_GET['password'];

    $q_patient = "select patientuser,password from patientlogin where patientuser='$username' and password='$password'";


    // check for empty result
    if ($result = mysqli_query($link, $q_patient)) {

        if ($result->num_rows > 0) {


            $response["error"] = "false";
            $response["type"] = "patient";
        } else {

            $q_manager = "select * from managers where user='$username'and password ='$password'";



            if ($result2 = mysqli_query($link, $q_manager)) {

                if ($result2->num_rows > 0) {

                    $response["error"] = "false";
                    $response["type"] = "manager";
                } else {
                    $response["error"] = "true";
                    $response["type"] = "";
                }
            } else {
                $response["error"] = "true";
                $response["type"] = "";
            }
        }
    } else {
        $response["error"] = "true";
        $response["type"] = "";
    }



    $json = json_encode($response);
    print_r($json);
}
