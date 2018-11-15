import json
import http.client

class ServiceDAO:

    connection = None
    enTetes = None

    def __init__(self, adresseServeur, authentification):
        self.connection = http.client.HTTPConnection(adresseServeur)
        self.enTetes = {'Content-type': 'application/json', 'Authentification' : authentification}
	
        if(self.connection):
            print("Connection au serveur NodeJS reussie")	

    def ajouter(self, contenuEnvoi):
        contenuEnvoiJSON = json.dumps(contenuEnvoi)
        print("JSON envoye au serveur NodeJS en POST : " + contenuEnvoiJSON)

        self.connection.request("POST", "/", contenuEnvoiJSON, self.enTetes)
        reponse = self.connection.getresponse()
        reponse = reponse.read().decode()

        return reponse

    def detruire(self):
        self.connection.close()
        print("Connection avec le serveur NodeJS fermee")

    if __name__ == '__main__':
        try:
            __init__(self, adresseServeur, authentification)
        except KeyboardInterrupt: 
            detruire()