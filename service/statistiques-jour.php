<?php 
	include "accesseur/statistiqueDAO.php";
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

	if(!empty($listeCapteursHeure))
	{
		$maxTestHeure = $listeCapteursHeure[0]->nombreCapteursHeure;
		$minTestHeure = $listeCapteursHeure[0]->nombreCapteursHeure;
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

				if(($heure->nombreCapteursHeure)>$maxTestHeure)
				{
					$maxTestHeure = $heure->nombreCapteursHeure;					
				}
				if(($heure->nombreCapteursHeure)<$minTestHeure)
				{
					$minTestHeure = $heure->nombreCapteursHeure;					
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
			<heure-valeur-max><?=$heureValeurMax->heure?></heure-valeur-max>
			<heure-valeur-min><?=$heureValeurMin->heure?></heure-valeur-min>
			<heure-max-tests><?=$heureMaxTests->heure?></heure-max-tests>
			<heure-min-tests><?=$heureMinTests->heure?></heure-min-tests>
		</synthese>
	</statistiques>
</statistiques-jour>