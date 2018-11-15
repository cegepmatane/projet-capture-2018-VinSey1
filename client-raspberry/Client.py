import datetime
import time
import random
from ServiceDAO import *
import MQ2 as CapteurGas

class Client:

    def configurer():
        ADRESSE_SERVEUR = "158.69.192.249:8080"
        AUTHENTIFICATION = "capture"
        global CapteurGas
        CapteurGas.configurer()
        global serviceDAO
        serviceDAO = ServiceDAO(ADRESSE_SERVEUR, AUTHENTIFICATION)

    def boucler():
        while True:
            tempsCourant = datetime.datetime.fromtimestamp(time.time()).strftime('%Y-%m-%d %H:%M:%S')
            position = {"latitude": 48.840689, "longitude": -67.497454}
            
            valeur = CapteurGas.recupererGas()
            #print(valeur)
            contenuEnvoi = {
                        "capteur": {
                        "pollution": {
                            "date": tempsCourant,
                            "position": position,
                            "valeur": valeur
                        }
                    }
            }
            
            reponse = serviceDAO.ajouter(contenuEnvoi)
            print(reponse)
            
            time.sleep(60)

    def detruire():
        CapteurGas.detruire()
        serviceDAO.detruire()
        print("Au revoir !")

    if __name__ == "__main__":
        try:
	        configurer()
	        boucler()
        except KeyboardInterrupt: 
	        detruire()
