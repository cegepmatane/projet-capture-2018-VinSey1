<?php 
	include "../accesseur/statistiqueDAO.php";
	

	$statDao = new StatistiqueDAO;

	$heureDernierTest = $statDao->verifierDernierTest();

	header("Content-type: text/xml");
	echo '<?xml version="1.0" encoding="UTF-8"?>';
	
	$heureCourante = date('H');
	

?>


<surveillance>
	<actif>
	<?=($heureDernierTest<$heureCourante-1) ? 0:1;?>
	</actif>
	<heure-courante>
	<?=$heureCourante ?>
	</heure-courante>
	<heure-dernier-test>
	<?=$heureDernierTest->heure?>
	</heure-dernier-test>
	

</surveillance>
    

