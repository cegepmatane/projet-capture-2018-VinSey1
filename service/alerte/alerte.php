<?php 
	include "../accesseur/statistiqueDAO.php";
	
	$jour = $_GET['jour'];
	$mois = $_GET['mois'];
	$annee = $_GET['annee'];
	$statDao = new StatistiqueDAO;

	$heureDernierTest = $statDao->verifierDernierTest($mois, $annee, $jour);

	header("Content-type: text/xml");
	echo '<?xml version="1.0" encoding="UTF-8"?>';
	
	

?>


<surveillance>
	<actif>
	
	</actif>
	<heure-dernier-test>
	<?=$heureDernierTest->heure?>
	</heure-dernier-test>
	

</surveillance>
    

