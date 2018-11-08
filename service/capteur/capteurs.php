<?php 
	header("Content-type: text/xml");
	echo '<?xml version="1.0" encoding="UTF-8"?>';
	include "../accesseur/capteurDAO.php";

	$capteurDao = new CapteurDAO;

	$listeCapteurs = $capteurDao->listerCapteurs();
	
	
?>
<capteurs>
	<?php foreach($listeCapteurs as $capteur) { ?>
		<capteur>
			<pollution>
				<id><?=$capteur->id?></id>
				<date><?=$capteur->date?></date>
				<position>
					<latitude><?=$capteur->latitude?></latitude>
					<longitude><?=$capteur->longitude?></longitude>
				</position>
				<valeur><?=$capteur->valeur?></valeur>
			</pollution>
		</capteur>
	<?php } ?>
</capteurs>