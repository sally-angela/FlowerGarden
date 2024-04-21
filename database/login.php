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

if (isset($_GET['username']) && isset($_GET['password'])) {
    $username = $_GET['username'];
    $password = $_GET['password'];
    $sql = "SELECT * FROM user WHERE username = ? AND password = ?";
    $stmt = $c->prepare($sql);
    $stmt->bind_param("is", $username, $password);
    $stmt->execute();
    $result = $stmt->get_result();

    // $result = $c->query($sql);

    // $obj = $result->fetch_object();
    $obj = $result->fetch_assoc();
    echo json_encode($obj);
    die();
}