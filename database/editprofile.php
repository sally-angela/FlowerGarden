<?php
error_reporting(E_ERROR | E_PARSE);
$c = new mysqli("localhost", "root", "", "flowergarden");

if ($c->connect_errno) {
    $arrayerror = array(
        'result' => 'ERROR',
        'msg' => 'Failed to connect DB'
    );
    echo json_encode($arrayerror);
    die();
}

if (isset($_GET['firstName']) && isset($_GET['lastName']) && isset($_GET['password']) && isset($_GET['id'])) {
    $firstName = $_GET['firstName'];
    $lastName = $_GET['lastName'];
    $password = $_GET['password'];
    $id = $_GET['id'];
    $sql = "UPDATE user SET firstName = '$firstName', lastName = '$lastName', password = '$password' WHERE id = '$id'";

    $result = $c->query($sql);

    if ($result) {
        $response = 'OK';
    } else {
        $response = 'ERROR';
    }
        
    echo json_encode($response);
    die();
}