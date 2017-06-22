<?php
$con = mysqli_connect('MySQLAdress', 'MySQLUsername', 'MySQLPassword', 'TableName');

    $name = $_POST['name'];
    $surname = $_POST['surname'];
    $email = $_POST['email'];
    $password = $_POST['password'];
    $statement = mysqli_prepare($con, "INSERT INTO users (name, email, surname, password) VALUES (?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "ssss", $name, $email, $surname, $password);
    mysqli_stmt_execute($statement);

    $response = array();
    $response['success'] = true;

    echo json_encode($response);
?>
