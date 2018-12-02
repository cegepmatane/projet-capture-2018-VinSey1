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

	if(filter_var($annee, FILTER_VALIDATE_INT)){
?>

	<statistiques-annee>
		<annee><?=$annee?></annee>
		<statistiques>
			<nombre-tests><?=$listeCapteursAnnee->nombrecapteursannee?></nombre-tests>
			<details>
				<?php foreach($listeCapteursMois as $mois){ ?>
					<mois>
						<date><?=$mois->mois?></date>
						<moyenne><?=$mois->moyennemois?></moyenne>
						<maximum><?=$mois->maximummois?></maximum>
						<minimum><?=$mois->minimummois?></minimum>
					</mois>
				<?php } ?>
			</details>
			<synthese>
				<moyenne-annee><?=($listeCapteursAnnee!=null) ? $listeCapteursAnnee->moyenneannee :"";?></moyenne-annee>
				<maximum-annee><?=($listeCapteursAnnee!=null) ? $listeCapteursAnnee->maximumannee:"";?></maximum-annee>
				<minimum-annee><?=($listeCapteursAnnee!=null) ? $listeCapteursAnnee->minimumannee:""?></minimum-annee>
				<mois-valeur-max><?=($moisValeurMax!=null) ? $moisValeurMax->mois:"";?></mois-valeur-max>
				<mois-valeur-min><?=($moisValeurMin!=null) ? $moisValeurMin->mois:"";?></mois-valeur-min>
				<mois-max-tests><?=($moisMaxTests!=null) ? $moisMaxTests->mois:"";?></mois-max-tests>
				<mois-min-tests><?=($moisMinTests!=null) ? $moisMinTests->mois:"";?></mois-min-tests>
			</synthese>
		</statistiques>
	</statistiques-annee>

<?php } else {
	echo("Les arguments ne sont pas valides");
} ?>
