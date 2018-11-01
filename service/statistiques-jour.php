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

	if(!empty($listeCapteursHeure))
	{
		$maximumHeure = $listeCapteursHeure[0]->maximumHeure;
		$minimumHeure = $listeCapteursHeure[0]->minimumHeure;
		$maxTestHeure = $listeCapteursHeure[0]->nombreCapteursHeure;
		$minTestHeure = $listeCapteursHeure[0]->nombreCapteursHeure;

		$heureValeurMax = $listeCapteursHeure[0]->heure;
		$heureValeurMin = $listeCapteursHeure[0]->heure;
		$heureMaxTest = $listeCapteursHeure[0]->heure;
		$heureMinTest = $listeCapteursHeure[0]->heure;

	}




	header("Content-type: text/xml");
	echo '<?xml version="1.0" encoding="UTF-8"?>';
?>

<statistiques-jour>
	<jour><?=$jour . "/" . $mois . "/" . $annee?></jour>
	<statistiques>
		<nombre-tests><?=$listeCapteursJour->nombreCapteursJour?></nombre-tests>
		<details>
			<?php foreach($listeCapteursHeure as $heure){ 
				
				if(($heure->maximumHeure)>$maximumHeure)
				{
					$maximumHeure = $heure->maximumHeure;
					$heureValeurMax=$heure->heure;
				}
				if(($heure->minimumHeure)<$minimumHeure)
				{
					$minimumHeure = $heure->minimumHeure;
					$heureValeurMin = $heure->heure;
				}
				if(($heure->nombreCapteursHeure)>$maxTestHeure)
				{
					$maxTestHeure = $heure->nombreCapteursHeure;
					$heureMaxTest=$heure->heure;
				}
				if(($heure->nombreCapteursHeure)<$minTestHeure)
				{
					$minTestHeure = $heure->nombreCapteursHeure;
					$heureMinTest=$heure->heure;
				}			
				
				?>
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
			<heure-valeur-max><?=$heureValeurMax?></heure-valeur-max>
			<heure-valeur-min><?=$heureValeurMin?></heure-valeur-min>
			<heure-max-tests><?=$heureMaxTest?></heure-max-tests>
			<heure-min-tests><?=$heureMinTest?></heure-min-tests>
		</synthese>
	</statistiques>
</statistiques-jour>