<?php 
	include "../accesseur/statistiqueDAO.php";
	
	$jour = $_GET['jour'];
	$mois = $_GET['mois'];
	$annee = $_GET['annee'];
	$statDao = new StatistiqueDAO;

	$heureDernierTest = $statDao->verifierDernierTest($annee, $mois, $jour);

	header("Content-type: text/xml");
	echo '<?xml version="1.0" encoding="UTF-8"?>';
	
	

?>


<surveillance>
	<actif>
	
	</actif>
	<date-dernier-test>
	<?=($heureDernierTest !=null) ? $heureDernierTest:"";?>
	</date-dernier-test>
	

</surveillance>
    

