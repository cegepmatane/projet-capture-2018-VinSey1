<?php
    $usager = 'scientifique';
    $motdepasse = 'password';
    $hote = 'localhost';
    $base = 'capture';
    $dsn = 'mysql:dbname='.$base.';host=' . $hote;
    $basededonnees = new PDO($dsn, $usager, $motdepasse);
?>