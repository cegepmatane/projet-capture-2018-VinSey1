<?php 
	header("Content-type: text/xml");
	echo '<?xml version="1.0" encoding="UTF-8"?>';
	include "connexion.php";

	$SQL_LISTE_CAPTEURS = "SELECT * FROM capteur";
	$requeteListeCapteurs = $basededonnees->prepare($SQL_LISTE_CAPTEURS);
	$requeteListeCapteurs->execute();
	$listeCapteurs = $requeteListeCapteurs->fetchAll(PDO::FETCH_OBJ);
	
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