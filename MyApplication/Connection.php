<?php

$connection = mysqli_connect('localhost','root','password','details');
if(!$connection){
    die("connection breaked" . mysql_error());
}

$servername="localhost";
$username="root";
$password="password";
$dbname="details";
$conn = new mysqli($servername,$username,$password,$dbname);
if($conn->connect_error){
    die("Connection failed". $conn->error);

}
?>