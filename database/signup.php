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

if (isset($_GET['username']) && isset($_GET['email']) && isset($_GET['firstName']) && isset($_GET['lastName']) && isset($_GET['password']) && isset($_GET['images'])) {
    $username = $_GET['username'];
    $email = $_GET['email'];
    $firstName = $_GET['firstName'];
    $lastName = $_GET['lastName'];
    $password = $_GET['password'];
    $images = $_GET['images'];
    $sql = "INSERT INTO user (username, email, firstName, lastName, password, images) VALUES ('$username', '$email', '$firstName', '$lastName', '$password', '$images')";

    $result = $c->query($sql);

    if ($result) {
        $response = 'OK';
    } else {
        $response = 'ERROR';
    }
        
    echo json_encode($response);
    die();
}