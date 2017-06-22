<?php
    $con = mysqli_connect('MySQLAdress', 'MySQLUsername', 'MySQLPassword', 'TableName');

    $email = $_POST['email'];
    $password = $_POST['password'];

    $statement = mysqli_prepare($con, 'SELECT * FROM users WHERE email = ? AND password = ?');
    mysqli_stmt_bind_param($statement, "ss", $email, $password);
    mysqli_stmt_execute($statement);

    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $user_id, $name, $surname, $email, $password);

    $response = array();
    $response['success'] = false;

    while(mysqli_stmt_fetch($statement)){
        $response['success'] = true;
        $response['name'] = $name;
        $response['email'] = $email;
        $response['surname'] = $surname;
        $response['password'] = $password;
    }

    echo json_encode($response);
?>
