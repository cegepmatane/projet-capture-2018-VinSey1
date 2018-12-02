<?php
    $usager = 'xmlcapture';
    $motdepasse = 'password';
    $hote = 'localhost';
    $base = 'capture';
    $dsn = 'pgsql:dbname=' .$base . ';host=' . $hote;
    $basededonnees = new PDO($dsn, $usager, $motdepasse);
?>
