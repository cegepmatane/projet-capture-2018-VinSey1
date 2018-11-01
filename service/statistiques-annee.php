<?php 
	include "connexion.php";
	$annee = $_GET['annee'];
	$SQL_LISTE_CAPTEURS_ANNEE = "SELECT COUNT(*) as nombreCapteursAnnee, AVG(valeur) as moyenneAnnee, MAX(valeur) as maximumAnnee, MIN(valeur) as minimumAnnee FROM capteur WHERE YEAR(date) =" . $annee;
	$requeteListeCapteursAnnee = $basededonnees->prepare($SQL_LISTE_CAPTEURS_ANNEE);
	$requeteListeCapteursAnnee->execute();
	$listeCapteursAnnee = $requeteListeCapteursAnnee->fetch(PDO::FETCH_OBJ);

	$SQL_LISTE_CAPTEURS_MOIS = "SELECT MONTH(date) as mois, COUNT(*) as nombreCapteursMois, AVG(valeur) as moyenneMois, MAX(valeur) as maximumMois, MIN(valeur) as minimumMois FROM capteur WHERE YEAR(date) =" . $annee . " GROUP BY MONTH(date)";
	$requeteListeCapteursMois = $basededonnees->prepare($SQL_LISTE_CAPTEURS_MOIS);
	$requeteListeCapteursMois->execute();
	$listeCapteursMois = $requeteListeCapteursMois->fetchAll(PDO::FETCH_OBJ);

	header("Content-type: text/xml");
	echo '<?xml version="1.0" encoding="UTF-8"?>';?>

<statistiques-annee>
	<annee><?=$annee?></annee>
	<statistiques>
		<nombre-tests><?=$listeCapteursAnnee->nombreCapteursAnnee?></nombre-tests>
		<details>
			<?php foreach($listeCapteursMois as $mois){ ?>
				<mois>
					<date><?=$mois->mois?></date>
					<moyenne><?=$mois->moyenneMois?></moyenne>
					<maximum><?=$mois->maximumMois?></maximum>
					<minimum><?=$mois->minimumMois?></minimum>
				</mois>
			<?php } ?>
		</details>
		<synthese>
			<moyenne-annee><?=$listeCapteursAnnee->moyenneAnnee?></moyenne-annee>
			<maximum-annee><?=$listeCapteursAnnee->maximumAnnee?></maximum-annee>
			<minimum-annee><?=$listeCapteursAnnee->minimumAnnee?></minimum-annee>
			<mois-valeur-max></mois-valeur-max>
			<mois-valeur-min></mois-valeur-min>
			<mois-max-tests></mois-max-tests>
			<mois-min-tests></mois-min-tests>
		</synthese>
	</statistiques>
</statistiques-annee>