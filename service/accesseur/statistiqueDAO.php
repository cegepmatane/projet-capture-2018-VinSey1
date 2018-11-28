<?php
include "connexion.php";
class StatistiqueDAO
{
    function recevoirListeCapteurAnnee($annee)
    {
        global $basededonnees;
        $SQL_LISTE_CAPTEURS_ANNEE = "SELECT COUNT(*) as nombreCapteursAnnee, AVG(valeur) as moyenneAnnee, MAX(valeur) as maximumAnnee, MIN(valeur) as minimumAnnee FROM capteur WHERE YEAR(date) = " . $annee;
	    $requeteListeCapteursAnnee = $basededonnees->prepare($SQL_LISTE_CAPTEURS_ANNEE);
	    $requeteListeCapteursAnnee->execute();
        $listeCapteursAnnee = $requeteListeCapteursAnnee->fetch(PDO::FETCH_OBJ);
        return $listeCapteursAnnee;
    }

    function recevoirListeCapteurMoisAnnee($annee)
    {
        global $basededonnees;
	    $SQL_LISTE_CAPTEURS_MOIS = "SELECT MONTH(date) as mois, COUNT(*) as nombreCapteursMois, AVG(valeur) as moyenneMois, MAX(valeur) as maximumMois, MIN(valeur) as minimumMois FROM capteur WHERE YEAR(date) = " . $annee . " GROUP BY MONTH(date)";
	    $requeteListeCapteursMois = $basededonnees->prepare($SQL_LISTE_CAPTEURS_MOIS);
	    $requeteListeCapteursMois->execute();
        $listeCapteursMois = $requeteListeCapteursMois->fetchAll(PDO::FETCH_OBJ);
        return $listeCapteursMois;
    }

    function recevoirMoisValeurMax($annee)
    {
        global $basededonnees;
	    $SQL_MOIS_VALEUR_MAX = "SELECT MONTH(date) as mois, valeur FROM capteur WHERE YEAR(date) = " . $annee . " AND valeur IN (SELECT MAX(valeur) FROM capteur WHERE YEAR(date) = " . $annee . ") GROUP BY YEAR(date)";
	    $requeteMoisValeurMax = $basededonnees->prepare($SQL_MOIS_VALEUR_MAX);
	    $requeteMoisValeurMax->execute();
        $moisValeurMax = $requeteMoisValeurMax->fetch(PDO::FETCH_OBJ);
        return $moisValeurMax;
    }

    function recevoirMoisValeurMin($annee)
    {
        global $basededonnees;
	    $SQL_MOIS_VALEUR_MIN = "SELECT MONTH(date) as mois, valeur FROM capteur WHERE YEAR(date) = " . $annee . " AND valeur IN (SELECT MIN(valeur) FROM capteur WHERE YEAR(date) = " . $annee . ") GROUP BY YEAR(date)";
	    $requeteMoisValeurMin = $basededonnees->prepare($SQL_MOIS_VALEUR_MIN);
	    $requeteMoisValeurMin->execute();
        $moisValeurMin = $requeteMoisValeurMin->fetch(PDO::FETCH_OBJ);
        return $moisValeurMin;
    }

    function recevoirMoisMaxTests($annee)
    {
        global $basededonnees;
	    $SQL_MOIS_MAX_TESTS = "SELECT MONTH(date) as mois FROM (SELECT COUNT(valeur) as nombreCapteursMois, date FROM capteur GROUP BY MONTH(date)) as nombreTests WHERE YEAR(date) =" . $annee . " ORDER BY nombreCapteursMois DESC LIMIT 1";
        $requeteMoisMaxTests = $basededonnees->prepare($SQL_MOIS_MAX_TESTS);
        $requeteMoisMaxTests->execute();
        $moisMaxTests = $requeteMoisMaxTests->fetch(PDO::FETCH_OBJ);
        return $moisMaxTests;
    }

    function recevoirMoisMinTests($annee)
    {
        global $basededonnees;
	    $SQL_MOIS_MIN_TESTS = "SELECT MONTH(date) as mois FROM (SELECT COUNT(valeur) as nombreCapteursMois, date FROM capteur GROUP BY MONTH(date)) as nombreTests WHERE YEAR(date) =" . $annee . " ORDER BY nombreCapteursMois ASC LIMIT 1";
        $requeteMoisMinTests = $basededonnees->prepare($SQL_MOIS_MIN_TESTS);
        $requeteMoisMinTests->execute();
        $moisMinTests = $requeteMoisMinTests->fetch(PDO::FETCH_OBJ);
        return $moisMinTests;
    }

    function recevoirListeCapteurMois($mois, $annee)
    {
        global $basededonnees;
        $SQL_LISTE_CAPTEURS_MOIS = "SELECT COUNT(*) as nombreCapteursMois, AVG(valeur) as moyenneMois, MAX(valeur) as maximumMois, MIN(valeur) as minimumMois FROM capteur WHERE MONTH(date) = " . $mois . " AND YEAR(date) = " . $annee;
	    $requeteListeCapteursMois = $basededonnees->prepare($SQL_LISTE_CAPTEURS_MOIS);
	    $requeteListeCapteursMois->execute();
        $listeCapteursMois = $requeteListeCapteursMois->fetch(PDO::FETCH_OBJ);
        return $listeCapteursMois;
    }

    function recevoirListeCapteurJourMois($mois, $annee)
    {
        global $basededonnees;
	    $SQL_LISTE_CAPTEURS_JOUR = "SELECT DAY(date) as jour, COUNT(*) as nombreCapteursJour, AVG(valeur) as moyenneJour, MAX(valeur) as maximumJour, MIN(valeur) as minimumJour FROM capteur WHERE MONTH(date) = " . $mois . " AND YEAR(date) = " . $annee . " GROUP BY DAY(date)";
	    $requeteListeCapteursJour = $basededonnees->prepare($SQL_LISTE_CAPTEURS_JOUR);
	    $requeteListeCapteursJour->execute();
	    $listeCapteursJour = $requeteListeCapteursJour->fetchAll(PDO::FETCH_OBJ);
        return $listeCapteursJour;
    }
    function recevoirJoursValeurMax($mois, $annee)
    {
        global $basededonnees;
	    $SQL_JOUR_VALEUR_MAX = "SELECT DAY(date) as jour, valeur FROM capteur WHERE YEAR(date) = " . $annee . " AND MONTH(date) = " . $mois . " AND valeur IN (SELECT MAX(valeur) FROM capteur WHERE YEAR(date) = " . $annee . " AND MONTH(date) = " . $mois . ") GROUP BY MONTH(date)";
	    $requeteJourValeurMax = $basededonnees->prepare($SQL_JOUR_VALEUR_MAX);
	    $requeteJourValeurMax->execute();
	    $jourValeurMax = $requeteJourValeurMax->fetch(PDO::FETCH_OBJ);
        return $jourValeurMax;
    }
    
    function recevoirJourValeurMin($mois, $annee)
    {
        global $basededonnees;
	    $SQL_JOUR_VALEUR_MIN = "SELECT DAY(date) as jour, valeur FROM capteur WHERE YEAR(date) = " . $annee . " AND MONTH(date) = " . $mois . " AND valeur IN (SELECT MIN(valeur) FROM capteur WHERE YEAR(date) = " . $annee . " AND MONTH(date) = " . $mois . ") GROUP BY MONTH(date)";
	    $requeteJourValeurMin = $basededonnees->prepare($SQL_JOUR_VALEUR_MIN);
	    $requeteJourValeurMin->execute();
	    $jourValeurMin = $requeteJourValeurMin->fetch(PDO::FETCH_OBJ);
        return $jourValeurMin;
    }

    function recevoirJourMaxTests($mois, $annee)
    {
        global $basededonnees;
	    $SQL_JOUR_MAX_TESTS = "SELECT DAY(date) as jour FROM (SELECT COUNT(valeur) as nombreCapteursJour, date FROM capteur GROUP BY DAY(date)) as nombreTests WHERE YEAR(date) =" . $annee . " AND MONTH(date) = " . $mois . " ORDER BY nombreCapteursJour DESC LIMIT 1";
        $requeteJourMaxTests = $basededonnees->prepare($SQL_JOUR_MAX_TESTS);
        $requeteJourMaxTests->execute();
        $jourMaxTests = $requeteJourMaxTests->fetch(PDO::FETCH_OBJ);
        return $jourMaxTests;
    }
    
    function recevoirJourMinTests($mois, $annee)
    {
        global $basededonnees;
	    $SQL_JOUR_MIN_TESTS = "SELECT DAY(date) as jour FROM (SELECT COUNT(valeur) as nombreCapteursJour, date FROM capteur GROUP BY DAY(date)) as nombreTests WHERE YEAR(date) =" . $annee . " AND MONTH(date) = " . $mois . " ORDER BY nombreCapteursJour ASC LIMIT 1";
        $requeteJourMinTests = $basededonnees->prepare($SQL_JOUR_MIN_TESTS);
        $requeteJourMinTests->execute();
        $jourMinTests = $requeteJourMinTests->fetch(PDO::FETCH_OBJ);;
        return $jourMinTests;
    }
    
    function recevoirListeCapteurJour($mois, $annee, $jour)
    {
        global $basededonnees;
        $SQL_LISTE_CAPTEURS_JOUR = "SELECT COUNT(*) as nombreCapteursJour, AVG(valeur) as moyenneJour, MAX(valeur) as maximumJour, MIN(valeur) as minimumJour FROM capteur WHERE YEAR(date) = " . $annee . " AND MONTH(date) = " . $mois . " AND DAY(date) = " . $jour;
	    $requeteListeCapteursJour = $basededonnees->prepare($SQL_LISTE_CAPTEURS_JOUR);
	    $requeteListeCapteursJour->execute();
	    $listeCapteursJour = $requeteListeCapteursJour->fetch(PDO::FETCH_OBJ);
        return $listeCapteursJour;
    }

    function recevoirListeCapteurHeure($mois, $annee, $jour)
    {
        global $basededonnees;
	    $SQL_LISTE_CAPTEURS_HEURE = "SELECT HOUR(date) as heure, COUNT(*) as nombreCapteursHeure, AVG(valeur) as moyenneHeure, MAX(valeur) as maximumHeure, MIN(valeur) as minimumHeure FROM capteur WHERE YEAR(date) = " . $annee . " AND MONTH(date) = " . $mois . " AND DAY(date) = " . $jour . " GROUP BY HOUR(date)";
	    $requeteListeCapteursHeure = $basededonnees->prepare($SQL_LISTE_CAPTEURS_HEURE);
	    $requeteListeCapteursHeure->execute();
	    $listeCapteursHeure = $requeteListeCapteursHeure->fetchAll(PDO::FETCH_OBJ);
        return $listeCapteursHeure;
    }

    function recevoirHeureValeurMax($mois, $annee, $jour)
    {
        global $basededonnees;
	    $SQL_HEURE_VALEUR_MAX = "SELECT HOUR(date) as heure, valeur FROM capteur WHERE YEAR(date) = " . $annee . " AND MONTH(date) = " . $mois . " AND DAY(date) = " . $jour . " AND valeur IN (SELECT MAX(valeur) FROM capteur WHERE YEAR(date) = " . $annee . " AND MONTH(date) = " . $mois . " AND DAY(date) = " . $jour . ") GROUP BY DAY(date)";
	    $requeteHeureValeurMax = $basededonnees->prepare($SQL_HEURE_VALEUR_MAX);
	    $requeteHeureValeurMax->execute();
	    $heureValeurMax = $requeteHeureValeurMax->fetch(PDO::FETCH_OBJ);
        return $heureValeurMax;
    }

    function recevoirHeureValeurMin($mois, $annee, $jour)
    {
        global $basededonnees;
	    $SQL_HEURE_VALEUR_MIN = "SELECT HOUR(date) as heure, valeur FROM capteur WHERE YEAR(date) = " . $annee . " AND MONTH(date) = " . $mois . " AND DAY(date) = " . $jour . " AND valeur IN (SELECT MIN(valeur) FROM capteur WHERE YEAR(date) = " . $annee . " AND MONTH(date) = " . $mois . " AND DAY(date) = " . $jour . ") GROUP BY DAY(date)";
	    $requeteHeureValeurMin = $basededonnees->prepare($SQL_HEURE_VALEUR_MIN);
	    $requeteHeureValeurMin->execute();
	    $heureValeurMin = $requeteHeureValeurMin->fetch(PDO::FETCH_OBJ);
        return $heureValeurMin;
    }

    function recevoirHeureMaxTests($mois, $annee, $jour)
    {
        global $basededonnees;
	    $SQL_HEURE_MAX_TESTS = "SELECT HOUR(date) as heure FROM (SELECT COUNT(valeur) as nombreCapteursHeure, date FROM capteur GROUP BY HOUR(date)) as nombreTests WHERE YEAR(date) =" . $annee . " AND MONTH(date) = " . $mois . " AND DAY(date) = " . $jour . " ORDER BY nombreCapteursHeure DESC LIMIT 1";
        $requeteHeureMaxTests = $basededonnees->prepare($SQL_HEURE_MAX_TESTS);
        $requeteHeureMaxTests->execute();
        $heureMaxTests = $requeteHeureMaxTests->fetch(PDO::FETCH_OBJ);
		return $heureMaxTests;
    }

    function recevoirHeureMinTests($mois, $annee, $jour)
    {
        global $basededonnees;
	    $SQL_HEURE_MIN_TESTS = "SELECT HOUR(date) as heure FROM (SELECT COUNT(valeur) as nombreCapteursHeure, date FROM capteur GROUP BY HOUR(date)) as nombreTests WHERE YEAR(date) =" . $annee . " AND MONTH(date) = " . $mois . " AND DAY(date) = " . $jour . " ORDER BY nombreCapteursHeure ASC LIMIT 1";
        $requeteHeureMinTests = $basededonnees->prepare($SQL_HEURE_MIN_TESTS);
        $requeteHeureMinTests->execute();
        $heureMinTests = $requeteHeureMinTests->fetch(PDO::FETCH_OBJ);
        return $heureMinTests;
    }
	
	function verifierDernierTest() {
		
		global $basededonnees;
		$SQL_DERNIER_TEST = "SELECT HOUR(date) as heure FROM capteur ORDER BY date DESC LIMIT 1";
		$requeteVerification = $basededonnees->prepare($SQL_DERNIER_TEST);
		$requeteVerification->execute();
		$heureDernierTest = $requeteVerification->fetch(PDO::FETCH_OBJ);
		return $heureDernierTest;
		
		
	}
}









?>