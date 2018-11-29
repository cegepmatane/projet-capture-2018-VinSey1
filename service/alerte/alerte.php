<?php 
	include "../accesseur/statistiqueDAO.php";
	

	$statDao = new StatistiqueDAO;

	$heureDernierTest = $statDao->verifierDernierTest();

	header("Content-type: text/xml");
	echo '<?xml version="1.0" encoding="UTF-8"?>';
	
	
	

?>


<surveillance>
	<actif>
	<?=($heureDernierTest+1>date('H')) ? 1:0;?>
	</actif>
	<heure-dernier-test>
	<?=$heureDernierTest->heure?>
	</heure-dernier-test>
	

</surveillance>
    

