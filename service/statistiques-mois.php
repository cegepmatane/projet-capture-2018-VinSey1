<?php 
	header("Content-type: text/xml");
	echo '<?xml version="1.0" encoding="UTF-8"?>';
	include "connexion.php";
	
	$SQL_LISTE_CAPTEURS = "SELECT * FROM capteur";
	$requeteListeCapteurs = $basededonnees->prepare($SQL_LISTE_CAPTEURS);
	$requeteListeCapteurs->execute();
	$listeCapteursBD = $requeteListeCapteurs->fetchAll(PDO::FETCH_OBJ);
	$mois = $_GET['mois'];
		
	foreach($listeCapteursBD as $capteur) {
		$moisCapteur = date("m",strtotime($capteur->date));
		if($moisCapteur == $mois){
			$listeDateCapteursParMois[] = $capteur->date;
			$listeValeurCapteursParMois[] = $capteur->valeur;
		}
	}
	/*
	for($i = 0; $i<count($listeDateCapteursParMois); $i++){
		$listeCapteursParJour = array(date("d", strtotime($listeDateCapteursParMois[$i])) => $listeValeurCapteursParMois[$i]);
		print_r($listeCapteursParJour);
	}*/
?>

<statistiques-mois>
	<mois><?=$mois?></mois>
	<statistiques>
		<nombre-tests><?=count($listeDateCapteursParMois)?></nombre-tests>
		<details>
			<?php /*foreach($listeCapteursParJour as $jour => $valeur) {*/ ?>
			<jour>
				<date></date>
				<moyenne></moyenne>
				<maximum></maximum>
				<minimum></minimum>
			</jour>
			<?php /*}*/ ?>
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