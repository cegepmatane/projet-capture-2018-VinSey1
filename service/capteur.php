<?php 
	header("Content-type: text/xml");
	echo '<?xml version="1.0" encoding="UTF-8"?>';
	include "connexion.php";
	
	$SQL_CAPTEUR = "SELECT * FROM capteur WHERE id = " . $_GET['id'];
	$requeteCapteur = $basededonnees->prepare($SQL_CAPTEUR);
	$requeteCapteur->execute();
	$capteur = $requeteCapteur->fetch(PDO::FETCH_OBJ);
	
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