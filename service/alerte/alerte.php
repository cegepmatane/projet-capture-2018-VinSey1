<?php 
	include "../accesseur/statistiqueDAO.php";

	$statDao = new StatistiqueDAO;

	$heureDernierTest = $statDao->verifierDernierTest();
	
	$listeCapteursHeure = $statDao->recevoirListeCapteurHeure(date('m'), date('Y'), date('d'));
	
	$valeurDangereuse = 0;
	foreach($listeCapteursHeure as $heure){
		if($heure->maximumheure > 500 || $heure->minimumheure > 500 || $heure->moyenneheure > 500){
			$valeurDangereuse = 1;
		}

		
		
		
	}

	header("Content-type: text/xml");
	echo '<?xml version="1.0" encoding="UTF-8"?>';
	
	
	
	$heureCourante = date('H');
?>

<surveillance>
	<actif><?=($heureDernierTest->heure == $heureCourante) ? 1:0;?></actif>
	<heure-courante><?=$heureCourante ?></heure-courante>
	<heure-dernier-test><?=$heureDernierTest->heure?></heure-dernier-test>
	<delai><?=$heureCourante-$heureDernierTest->heure?></delai>
	<valeur-dangereuse><?=$valeurDangereuse?></valeur-dangereuse>
</surveillance>