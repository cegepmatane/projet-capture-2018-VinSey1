<?php 
	header("Content-type: text/xml");
	echo '<?xml version="1.0" encoding="UTF-8"?>';
	include "connexion.php";	
?>

<statistiques-mois>
	<mois></mois>
	<statistiques>
		<nombre-tests></nombre-tests>
		<details>
			<jour>
				<date></date>
				<moyenne></moyenne>
				<maximum></maximum>
				<minimum></minimum>
			</jour>
		</details>
		<synthese>
			<moyenne-mois></moyenne-mois>
			<maximum-mois></maximum-mois>
			<minimum-mois></minimum-mois>
			<jour-valeur-max></jour-valeur-max>
			<jour-valeur-min></jour-valeur-min>
			<jour-max-tests></jour-max-tests>
			<jour-min-tests></jour-min-tests>
		</synthese>
	</statistiques>
</statistiques-mois>