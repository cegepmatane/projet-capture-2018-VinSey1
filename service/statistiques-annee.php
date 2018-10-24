<?php 
	header("Content-type: text/xml");
	echo '<?xml version="1.0" encoding="UTF-8"?>';
	include "connexion.php";	
?>

<statistiques-annee>
	<annee></annee>
	<statistiques>
		<nombre-tests></nombre-tests>
		<details>
			<mois>
				<date></date>
				<moyenne></moyenne>
				<maximum></maximum>
				<minimum></minimum>
			</mois>
		</details>
		<synthese>
			<moyenne-annee></moyenne-annee>
			<maximum-annee></maximum-annee>
			<minimum-annee></minimum-annee>
			<mois-valeur-max></mois-valeur-max>
			<mois-valeur-min></mois-valeur-min>
			<mois-max-tests></mois-max-tests>
			<mois-min-tests></mois-min-tests>
		</synthese>
	</statistiques>
</statistiques-annee>
