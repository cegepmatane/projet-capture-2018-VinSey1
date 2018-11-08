<?php
include "connexion.php";
class CapteurDAO
{
    function listerCapteurs()
    {
        global $basededonnees;
        $SQL_LISTE_CAPTEURS = "SELECT * FROM capteur";
	    $requeteListeCapteurs = $basededonnees->prepare($SQL_LISTE_CAPTEURS);
	    $requeteListeCapteurs->execute();
        $listeCapteurs = $requeteListeCapteurs->fetchAll(PDO::FETCH_OBJ);
        return $listeCapteurs;
    }

    function recevoirCapteur($id)
    {
        $SQL_CAPTEUR = "SELECT * FROM capteur WHERE id = " . $id;
	    $requeteCapteur = $basededonnees->prepare($SQL_CAPTEUR);
	    $requeteCapteur->execute();
        $capteur = $requeteCapteur->fetch(PDO::FETCH_OBJ);
        return $capteur;
    }





}




?>