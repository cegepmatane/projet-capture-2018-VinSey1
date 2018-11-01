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

	$SQL_MOIS_VALEUR_MAX = "SELECT MONTH(date) as mois, valeur FROM capteur WHERE YEAR(date) = " . $annee . " AND valeur IN (SELECT MAX(valeur) FROM capteur GROUP BY YEAR(date))";
	$requeteMoisValeurMax = $basededonnees->prepare($SQL_MOIS_VALEUR_MAX);
	$requeteMoisValeurMax->execute();
	$moisValeurMax = $requeteMoisValeurMax->fetch(PDO::FETCH_OBJ);;

	if(!empty($listeCapteursMois))
	{
		$minimumMois = $listeCapteursMois[0]->minimumMois;
		$maxTestMois= $listeCapteursMois[0]->nombreCapteursMois;
		$minTestMois= $listeCapteursMois[0]->nombreCapteursMois;

		$moisValeurMin = $listeCapteursMois[0]->mois;
		$moisMaxTest = $listeCapteursMois[0]->mois;
		$moisMinTest = $listeCapteursMois[0]->mois;

	}

	header("Content-type: text/xml");
	echo '<?xml version="1.0" encoding="UTF-8"?>';?>

<statistiques-annee>
	<annee><?=$annee?></annee>
	<statistiques>
		<nombre-tests><?=$listeCapteursAnnee->nombreCapteursAnnee?></nombre-tests>
		<details>
			<?php foreach($listeCapteursMois as $mois){
				
				if(($mois->minimumMois)<$minimumMois)
				{
					$minimumMois = $mois->minimumMois;
					$moisValeurMin = $mois->mois;
				}
				if(($mois->nombreCapteursMois)>$maxTestMois)
				{
					$maxTestMois = $mois->nombreCapteursMois;
					$moisMaxTest=$mois->mois;
				}
				if(($mois->nombreCapteursMois)<$minTestMois)
				{
					$minTestMois = $mois->nombreCapteursMois;
					$moisMinTest=$mois->mois;
				}
				
				?>
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
			<mois-valeur-max><?=$moisValeurMax->mois?></mois-valeur-max>
			<mois-valeur-min><?=$moisValeurMin?></mois-valeur-min>
			<mois-max-tests><?=$moisMaxTest?></mois-max-tests>
			<mois-min-tests><?=$moisMinTest?></mois-min-tests>
		</synthese>
	</statistiques>
</statistiques-annee>