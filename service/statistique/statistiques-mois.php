<?php
	include "../accesseur/statistiqueDAO.php";
	$mois = $_GET['mois'];
	$annee = $_GET['annee'];
	$statDao = new StatistiqueDAO;

	$listeCapteursMois = $statDao->recevoirListeCapteurMois($mois, $annee); 

	$listeCapteursJour = $statDao->recevoirListeCapteurJourMois($mois, $annee);
	
	$jourValeurMax = $statDao->recevoirJoursValeurMax($mois, $annee);
		
	$jourValeurMin = $statDao->recevoirJourValeurMin($mois, $annee);
	
	$jourMaxTests = $statDao->recevoirJourMaxTests($mois, $annee);

	$jourMinTests = $statDao->recevoirJourMinTests($mois, $annee);
	
	if(!empty($listeCapteursJour))
	{
		$maxTestJour = $listeCapteursJour[0]->nombreCapteursJour;
		$minTestJour = $listeCapteursJour[0]->nombreCapteursJour;
	}
	
	header("Content-type: text/xml");
	echo '<?xml version="1.0" encoding="UTF-8"?>';

	$arguments = array($annee, $mois);

	if(filter_var_array($arguments, FILTER_VALIDATE_INT)){
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
				<moyenne-mois><?=($listeCapteursMois !=null) ? $listeCapteursMois->moyenneMois:"";?></moyenne-mois>
				<maximum-mois><?=($listeCapteursMois !=null) ? $listeCapteursMois->maximumMois:"";?></maximum-mois>
				<minimum-mois><?=($listeCapteursMois !=null) ? $listeCapteursMois->minimumMois:"";?></minimum-mois>
				<jour-valeur-max><?=($jourValeurMax !=null) ? $jourValeurMax->jour:"";?></jour-valeur-max>
				<jour-valeur-min><?=($jourValeurMin !=null) ? $jourValeurMin->jour:"";?></jour-valeur-min>
				<jour-max-test><?=($jourMaxTests !=null) ? $jourMaxTests->jour:"";?></jour-max-test>
				<jour-min-test><?=($jourMinTests !=null) ? $jourMinTests->jour:"";?></jour-min-test>
			</synthese>
		</statistiques>
	</statistiques-mois>

<?php } else {
	echo("Les arguments ne sont pas valides");
} ?>