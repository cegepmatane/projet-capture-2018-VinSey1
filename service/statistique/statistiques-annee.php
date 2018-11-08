<?php 
	include "../accesseur/statistiqueDAO.php";
	$annee = $_GET['annee'];

	$statDao = new StatistiqueDAO;
	
	
	$listeCapteursAnnee = $statDao->recevoirListeCapteurAnnee($annee);

	
	$listeCapteursMois = $statDao->recevoirListeCapteurMoisAnnee($annee);

	$moisValeurMax = $statDao->recevoirMoisValeurMax($annee);

	$moisValeurMin = $statDao->recevoirMoisValeurMin($annee);
	
	$moisMaxTests =  $statDao->recevoirMoisMaxTests($annee);
	
    $moisMinTests = $statDao->recevoirMoisMinTests($annee);
	
	header("Content-type: text/xml");
	echo '<?xml version="1.0" encoding="UTF-8"?>';
?>

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
			<mois-valeur-max><?=$moisValeurMax->mois?></mois-valeur-max>
			<mois-valeur-min><?=$moisValeurMin->mois?></mois-valeur-min>
			<mois-max-tests><?=$moisMaxTests->mois?></mois-max-tests>
			<mois-min-tests><?=$moisMinTests->mois?></mois-min-tests>
		</synthese>
	</statistiques>
</statistiques-annee>
