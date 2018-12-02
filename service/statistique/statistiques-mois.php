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
	
	header("Content-type: text/xml");
	echo '<?xml version="1.0" encoding="UTF-8"?>';

	$arguments = array($annee, $mois);

	if(filter_var_array($arguments, FILTER_VALIDATE_INT)){
?>

	<statistiques-mois>
		<mois><?=$mois . "/" . $annee?></mois>
		<statistiques>
			<nombre-tests><?=$listeCapteursMois->nombrecapteursmois?></nombre-tests>
			<details>
				<?php foreach($listeCapteursJour as $jour){ ?>
					<jour>
						<date><?=$jour->jour?></date>
						<moyenne><?=$jour->moyennejour?></moyenne>
						<maximum><?=$jour->maximumjour?></maximum>
						<minimum><?=$jour->minimumjour?></minimum>
					</jour>
				<?php } ?>
			</details>
			<synthese>
				<moyenne-mois><?=($listeCapteursMois !=null) ? $listeCapteursMois->moyennemois:"";?></moyenne-mois>
				<maximum-mois><?=($listeCapteursMois !=null) ? $listeCapteursMois->maximummois:"";?></maximum-mois>
				<minimum-mois><?=($listeCapteursMois !=null) ? $listeCapteursMois->minimummois:"";?></minimum-mois>
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