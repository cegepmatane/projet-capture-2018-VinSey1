<?php 
	header("Content-type: text/xml");
	echo '<?xml version="1.0" encoding="UTF-8"?>';
	include "connexion.php";	
?>

<statistiques-jour>
	<jour></jour>
	<statistiques>
		<nombre-tests></nombre-tests>
		<details>
			<heure>
				<horaire></horaire>
				<moyenne></moyenne>
				<maximum></maximum>
				<minimum></minimum>
			</heure>
		</details>
		<synthese>
			<moyenne-jour></moyenne-jour>
			<maximum-jour></maximum-jour>
			<minimum-jour></minimum-jour>
			<heure-valeur-max></heure-valeur-max>
			<heure-valeur-min></heure-valeur-min>
			<heure-max-tests></heure-max-tests>
			<heure-min-tests></heure-min-tests>
		</synthese>
	</statistiques>
</statistiques-jour>