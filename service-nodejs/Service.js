var http = require('http');
var capteurDAO = require('./accesseur/CapteurDAO');

const CLEF_ECHANGE = "capture";

var serveur = http.createServer((requete, reponse) => {
	reponse.writeHead(200, { 'Content-Type': 'text/plain' });
	var authentificationClient = requete.headers.authentification;
	var adresseIPRequete = requete.headers['x-forwarded-for'] || requete.connection.remoteAddress || requete.socket.remoteAddress ||
                                     (requete.connection.socket ? requete.connection.socket.remoteAddress : null);

	if(CLEF_ECHANGE === authentificationClient){
		if('GET' === requete.method){
			console.log("Requete GET de " + adresseIPRequete);
			reponse.end('Requete GET recu par le serveur NodeJS.');
		}
		if('POST' === requete.method){
		        console.log("Requete POST de " + adresseIPRequete);
			reponse.end("Requete POST recu par le serveur NodeJS.");

        		var uri = '';
        		requete.on('data', message => { uri += message; });
		        requete.on('end', function(){
				uri = decodeURI(uri);

				var capteur = JSON.parse(uri).capteur;
//				capteurDAO.ajouterCapteur(capteur);
        		});
		}
	} else {
		console.log('\x1b[31m');
		console.log('!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!');
		console.log('!!!!!!!!!!!!!!!!!!!!!!!\tAcces non authorisé de ' + adresseIPRequete  + ' @ ' + new Date() + ' !!!!!!!!!!!!!!!!!!!!!!!');
		console.log('!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!');
		console.log('\x1b[0m');
		reponse.end('Acces interdit, vous ne possedez pas les droits à ce serveur.');
	}
	// Termine l'envoi de la demande et si des parties du corps ne sont pas envoyées, 
	// cela les jettera dans le flux.
	reponse.end('');
}).listen(8080);
