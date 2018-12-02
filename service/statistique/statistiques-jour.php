<?php 
	include "../accesseur/statistiqueDAO.php";
	$jour = $_GET['jour'];
	$mois = $_GET['mois'];
	$annee = $_GET['annee'];
	$statDao = new StatistiqueDAO;

	$listeCapteursJour = $statDao->recevoirListeCapteurJour($mois, $annee, $jour);

	$listeCapteursHeure = $statDao->recevoirListeCapteurHeure($mois, $annee, $jour);

	$heureValeurMax = $statDao->recevoirHeureValeurMax($mois, $annee, $jour);

	$heureValeurMin = $statDao->recevoirHeureValeurMin($mois, $annee, $jour);
	
	$heureMaxTests = $statDao->recevoirHeureMaxTests($mois, $annee, $jour);
	
    $heureMinTests = $statDao->recevoirHeureMinTests($mois, $annee, $jour);

	header("Content-type: text/xml");
	echo '<?xml version="1.0" encoding="UTF-8"?>';

	$arguments = array($annee, $mois, $jour);

	if(filter_var_array($arguments, FILTER_VALIDATE_INT)){
?>

	<statistiques-jour>
		<jour><?=$jour . "/" . $mois . "/" . $annee?></jour>
		<statistiques>
			<nombre-tests><?=$listeCapteursJour->nombrecapteursjour?></nombre-tests>
			<details>
				<?php foreach($listeCapteursHeure as $heure){ ?>
					<heure>
						<horaire><?=$heure->heure?></horaire>
						<moyenne><?=$heure->moyenneheure?></moyenne>
						<maximum><?=$heure->maximumheure?></maximum>
						<minimum><?=$heure->minimumheure?></minimum>
					</heure>
				<?php } ?>
			</details>
			<synthese>		
				<moyenne-jour><?=($listeCapteursJour !=null) ? $listeCapteursJour->moyennejour:"";?></moyenne-jour>
				<maximum-jour><?=($listeCapteursJour !=null) ? $listeCapteursJour->maximumjour:"";?></maximum-jour>
				<minimum-jour><?=($listeCapteursJour !=null) ? $listeCapteursJour->minimumjour:"";?></minimum-jour>
				<heure-valeur-max><?=($heureValeurMax !=null) ? $heureValeurMax->heure:"";?></heure-valeur-max>
				<heure-valeur-min><?=($heureValeurMin !=null) ? $heureValeurMin->heure:"";?></heure-valeur-min>
				<heure-max-tests><?=($heureMaxTests !=null) ? $heureMaxTests->heure:"";?></heure-max-tests>
				<heure-min-tests><?=($heureMinTests !=null) ? $heureMinTests->heure:"";?></heure-min-tests>
			</synthese>
		</statistiques>
	</statistiques-jour>

<?php } else {
	echo("Les arguments ne sont pas valides");
} ?>