<?php 
	header("Content-type: text/xml");
	echo '<?xml version="1.0" encoding="UTF-8"?>';
	include "accesseur/capteurDAO.php";

	$capteurDao = new CapteurDAO;
	$id = $_GET['id'];
	$capteur = $capteurDao-> recevoirCapteur($id);
	
?>

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