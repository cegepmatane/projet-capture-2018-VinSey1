var http = require('http');

var serveur = http.createServer(async function(requete, reponse) {	
	if('GET' === requete.method){
		console.log("Requete GET");
	}
	if('POST' === requete.method){
		console.log("Requete POST");
    }
    
    // Termine l'envoi de la demande et si des parties du corps ne sont pas envoyées, 
    // cela les jettera dans le flux.
	reponse.end('');
}).listen(8080);