<?php
include_once("Connection.php");
?>


<?php
  $name = $_GET['username'];
  $pwd = $_GET['password'];

 $que = "INSERT INTO usercredentials (`Username`, `Password`) VALUES ('$name','$pwd')";
  $result = mysqli_query( $connection,$que);
if(!$result) {
    echo "error in mysql result";
}


