<?php
	include "connexion.php";
	$mois = $_GET['mois'];
	$SQL_LISTE_CAPTEURS_MOIS = "SELECT COUNT(*) as nombreCapteurs, AVG(valeur) as moyenneMois, MAX(valeur) as maximumMois, MIN(valeur) as minimumMois FROM capteur WHERE MONTH(date) =" . $mois;
	$requeteListeCapteursMois = $basededonnees->prepare($SQL_LISTE_CAPTEURS_MOIS);
	$requeteListeCapteursMois->execute();
	$listeCapteursMois = $requeteListeCapteursMois->fetch(PDO::FETCH_OBJ);

	$SQL_LISTE_CAPTEURS_PAR_JOUR = "SELECT DAY(date) as jour, COUNT(*) as nombreCapteurs, AVG(valeur) as moyenneJour, MAX(valeur) as maximumJour, MIN(valeur) as minimumJour FROM capteur WHERE MONTH(date) =" . $mois . " GROUP BY DAY(date)";
	$requeteListeCapteursJour = $basededonnees->prepare($SQL_LISTE_CAPTEURS_PAR_JOUR);
	$requeteListeCapteursJour->execute();
	$listeCapteursJour = $requeteListeCapteursJour->fetchAll(PDO::FETCH_OBJ);

	header("Content-type: text/xml");
	echo '<?xml version="1.0" encoding="UTF-8"?>';
?>
<statistiques-mois>
	<mois><?=$mois?></mois>
	<statistiques>
		<nombre-tests><?=$listeCapteursMois->nombreCapteurs?></nombre-tests>
		<details>
			<?php foreach($listeCapteursJour as $jour){ ?>
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
			<jour-valeur-max></jour-valeur-max>
			<jour-valeur-min></jour-valeur-min>
			<jour-max-tests></jour-max-tests>
			<jour-min-tests></jour-min-tests>
		</synthese>
	</statistiques>
</statistiques-mois>