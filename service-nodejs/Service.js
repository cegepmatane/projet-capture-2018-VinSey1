var http = require('http');
var capteurDAO = require('./accesseur/CapteurDAO');

var serveur = http.createServer((requete, reponse) => {	
	if('GET' === requete.method){
		console.log("Requete GET");
	}
	if('POST' === requete.method){
        console.log("Requete POST");
        
        var uri = '';
        requete.on('data', message => { uri += message; });
        requete.on('end', function(){
            uri = decodeURI(uri);
            
            var capteur = JSON.parse(uri).capteur;
            capteurDAO.ajouterCapteur(capteur);
        });
    }
    
    // Termine l'envoi de la demande et si des parties du corps ne sont pas envoyées, 
    // cela les jettera dans le flux.
	reponse.end('');
}).listen(8080);