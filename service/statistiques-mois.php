<?php
	include "connexion.php";
	$mois = $_GET['mois'];
	$annee = $_GET['annee'];
	$SQL_LISTE_CAPTEURS_MOIS = "SELECT COUNT(*) as nombreCapteursMois, AVG(valeur) as moyenneMois, MAX(valeur) as maximumMois, MIN(valeur) as minimumMois FROM capteur WHERE MONTH(date) = " . $mois . " AND YEAR(date) = " . $annee;
	$requeteListeCapteursMois = $basededonnees->prepare($SQL_LISTE_CAPTEURS_MOIS);
	$requeteListeCapteursMois->execute();
	$listeCapteursMois = $requeteListeCapteursMois->fetch(PDO::FETCH_OBJ);

	$SQL_LISTE_CAPTEURS_JOUR = "SELECT DAY(date) as jour, COUNT(*) as nombreCapteursJour, AVG(valeur) as moyenneJour, MAX(valeur) as maximumJour, MIN(valeur) as minimumJour FROM capteur WHERE MONTH(date) = " . $mois . " AND YEAR(date) = " . $annee . " GROUP BY DAY(date)";
	$requeteListeCapteursJour = $basededonnees->prepare($SQL_LISTE_CAPTEURS_JOUR);
	$requeteListeCapteursJour->execute();
	$listeCapteursJour = $requeteListeCapteursJour->fetchAll(PDO::FETCH_OBJ);
	
	$SQL_JOUR_VALEUR_MAX = "SELECT DAY(date) as jour, valeur FROM capteur WHERE YEAR(date) = " . $annee . " AND MONTH(date) = " . $mois . " AND valeur IN (SELECT MAX(valeur) FROM capteur WHERE YEAR(date) = " . $annee . " AND MONTH(date) = " . $mois . ") GROUP BY MONTH(date)";
	$requeteJourValeurMax = $basededonnees->prepare($SQL_JOUR_VALEUR_MAX);
	$requeteJourValeurMax->execute();
	$jourValeurMax = $requeteJourValeurMax->fetch(PDO::FETCH_OBJ);;
		
	$SQL_JOUR_VALEUR_MIN = "SELECT DAY(date) as jour, valeur FROM capteur WHERE YEAR(date) = " . $annee . " AND MONTH(date) = " . $mois . " AND valeur IN (SELECT MIN(valeur) FROM capteur WHERE YEAR(date) = " . $annee . " AND MONTH(date) = " . $mois . ") GROUP BY MONTH(date)";
	$requeteJourValeurMin = $basededonnees->prepare($SQL_JOUR_VALEUR_MIN);
	$requeteJourValeurMin->execute();
	$jourValeurMin = $requeteJourValeurMin->fetch(PDO::FETCH_OBJ);;
	
	
	$SQL_JOUR_MAX_TESTS = "SELECT DAY(date) as jour FROM (SELECT COUNT(valeur) as nombreCapteursJour, date FROM capteur GROUP BY DAY(date)) as nombreTests WHERE YEAR(date) =" . $annee . " AND MONTH(date) = " . $mois . " ORDER BY nombreCapteursJour DESC LIMIT 1";
    $requeteJourMaxTests = $basededonnees->prepare($SQL_JOUR_MAX_TESTS);
    $requeteJourMaxTests->execute();
    $jourMaxTests = $requeteJourMaxTests->fetch(PDO::FETCH_OBJ);;
		
	$SQL_JOUR_MIN_TESTS = "SELECT DAY(date) as jour FROM (SELECT COUNT(valeur) as nombreCapteursJour, date FROM capteur GROUP BY DAY(date)) as nombreTests WHERE YEAR(date) =" . $annee . " AND MONTH(date) = " . $mois . "ORDER BY nombreCapteursJour ASC LIMIT 1";
    $requeteJourMinTests = $basededonnees->prepare($SQL_JOUR_MIN_TESTS);
    $requeteJourMinTests->execute();
    $jourMinTests = $requeteJourMinTests->fetch(PDO::FETCH_OBJ);;
	
	if(!empty($listeCapteursJour))
	{
		$maxTestJour = $listeCapteursJour[0]->nombreCapteursJour;
		$minTestJour = $listeCapteursJour[0]->nombreCapteursJour;

	}
	
	header("Content-type: text/xml");
	echo '<?xml version="1.0" encoding="UTF-8"?>';
?>
<statistiques-mois>
	<mois><?=$mois . "/" . $annee?></mois>
	<statistiques>
		<nombre-tests><?=$listeCapteursMois->nombreCapteursMois?></nombre-tests>
		<details>
			<?php foreach($listeCapteursJour as $jour){

				if(($jour->nombreCapteursJour)>$maxTestJour)
				{
					$maxTestJour = $jour->nombreCapteursJour;
					
				}
				if(($jour->nombreCapteursJour)<$minTestJour)
				{
					$minTestJour = $jour->nombreCapteursJour;
					
				}

				?>
				<jour>
					<date><?=$jour->jour?></date>
					<moyenne><?=$jour->moyenneJour?></moyenne>
					<maximum><?=$jour->maximumJour?></maximum>
					<minimum><?=$jour->minimumJour?></minimum>
				</jour>
			<?php } ?>
		</details>
		<synthese>
			<moyenne-mois><?=$listeCapteursMois->moyenneMois?></moyenne-mois>
			<maximum-mois><?=$listeCapteursMois->maximumMois?></maximum-mois>
			<minimum-mois><?=$listeCapteursMois->minimumMois?></minimum-mois>
			<jour-valeur-max><?=$jourValeurMax->jour?></jour-valeur-max>
			<jour-valeur-min><?=$jourValeurMin->jour?></jour-valeur-min>

			
		</synthese>
	</statistiques>
</statistiques-mois>