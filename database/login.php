<?php
error_reporting(E_ERROR | E_PARSE);
$c = new mysqli("localhost", "root", "", "flowergarden");

if ($c->connect_errno) {
    $arrayerror = array('result' => 'ERROR',
        'msg' => 'Failed to connect DB');
    echo json_encode($arrayerror);
    die();
}

if (isset($_GET['username']) && isset($_GET['password'])) {
    $username = $_GET['username'];
    $password = $_GET['password'];
    $sql = "SELECT * FROM user WHERE username = '$username' AND password = '$password'";

    $result = $c->query($sql);

    $obj = $result->fetch_object();
    echo json_encode($obj);
    die();
}