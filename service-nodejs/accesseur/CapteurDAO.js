var postgresql = require('pg');
//var informationConnexion = "postgres://pseudo:mdp@localhost:5432/bd";
var informationConnexion = "postgres://jsoncapture:password@localhost:5432/capture";

exports.ajouterCapteur = async function(capteur){
	console.log("CapteurDAO.ajouterCapteur(" + JSON.stringify(capteur) + ")");

	var baseDeDonnees = new postgresql.Client(informationConnexion);
	await baseDeDonnees.connect();

	var sql = "insert into capteur(date, latitude, longitude, valeur) values('{{date}}','{{latitude}}','{{longitude}}','{{valeur}}');";
	sql = sql.replace("{{date}}", capteur.pollution.date).replace("{{latitude}}", capteur.pollution.position.latitude).replace("{{longitude}}", capteur.pollution.position.longitude).replace("{{valeur}}", capteur.pollution.valeur);

	console.log(sql);
	baseDeDonnees.query(sql); //await
}
