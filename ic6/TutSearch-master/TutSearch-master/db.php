<?php

define('DBHOST', '127.0.0.1');
 define('DBUSER', 'root');
 define('DBPASS', 'Shabad@97');
 define('DBNAME', 'tutsearch');
 
 $conn = mysql_connect(DBHOST,DBUSER,DBPASS);
 $dbcon = mysql_select_db(DBNAME);
 
 if ( !$conn ) {
  die("Connection failed : " . mysql_error());
 }
 
 if ( !$dbcon ) {
  die("Database Connection failed : " . mysql_error());
 }

?>