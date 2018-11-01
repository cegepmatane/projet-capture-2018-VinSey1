<?php 
	include "connexion.php";
	$jour = $_GET['jour'];
	$mois = $_GET['mois'];
	$annee = $_GET['annee'];
	$SQL_LISTE_CAPTEURS_JOUR = "SELECT COUNT(*) as nombreCapteursJour, AVG(valeur) as moyenneJour, MAX(valeur) as maximumJour, MIN(valeur) as minimumJour FROM capteur WHERE YEAR(date) =" . $annee . " AND MONTH(date) =" . $mois . " AND DAY(date) =" . $jour;
	$requeteListeCapteursJour = $basededonnees->prepare($SQL_LISTE_CAPTEURS_JOUR);
	$requeteListeCapteursJour->execute();
	$listeCapteursJour = $requeteListeCapteursJour->fetch(PDO::FETCH_OBJ);

	$SQL_LISTE_CAPTEURS_HEURE = "SELECT HOUR(date) as heure, COUNT(*) as nombreCapteursHeure, AVG(valeur) as moyenneHeure, MAX(valeur) as maximumHeure, MIN(valeur) as minimumHeure FROM capteur WHERE YEAR(date) =" . $annee . " AND MONTH(date) =" . $mois . " AND DAY(date) =" . $jour . " GROUP BY HOUR(date)";
	$requeteListeCapteursHeure = $basededonnees->prepare($SQL_LISTE_CAPTEURS_HEURE);
	$requeteListeCapteursHeure->execute();
	$listeCapteursHeure = $requeteListeCapteursHeure->fetchAll(PDO::FETCH_OBJ);

	header("Content-type: text/xml");
	echo '<?xml version="1.0" encoding="UTF-8"?>';
?>

<statistiques-jour>
	<jour><?=$jour . "/" . $mois . "/" . $annee?></jour>
	<statistiques>
		<nombre-tests><?=$listeCapteursJour->nombreCapteursJour?></nombre-tests>
		<details>
			<?php foreach($listeCapteursHeure as $heure){ ?>
				<heure>
					<horaire><?=$heure->heure?></horaire>
					<moyenne><?=$heure->moyenneHeure?></moyenne>
					<maximum><?=$heure->maximumHeure?></maximum>
					<minimum><?=$heure->minimumHeure?></minimum>
				</heure>
			<?php } ?>
		</details>
		<synthese>
			<moyenne-jour><?=$listeCapteursJour->moyenneJour?></moyenne-jour>
			<maximum-jour><?=$listeCapteursJour->maximumJour?></maximum-jour>
			<minimum-jour><?=$listeCapteursJour->minimumJour?></minimum-jour>
			<heure-valeur-max></heure-valeur-max>
			<heure-valeur-min></heure-valeur-min>
			<heure-max-tests></heure-max-tests>
			<heure-min-tests></heure-min-tests>
		</synthese>
	</statistiques>
</statistiques-jour>