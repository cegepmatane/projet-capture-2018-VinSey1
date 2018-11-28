<?php 
	include "../accesseur/statistiqueDAO.php";
	

	$statDao = new StatistiqueDAO;

	$heureDernierTest = $statDao->verifierDernierTest();

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
    

