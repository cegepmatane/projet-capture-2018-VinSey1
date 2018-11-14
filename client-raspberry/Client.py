import datetime
import time
import random
from ServiceDAO import *

class Client:

    ADRESSE_SERVEUR = "158.69.192.249:8080"
    AUTHENTIFICATION = "capture"

    serviceDAO = ServiceDAO(ADRESSE_SERVEUR, AUTHENTIFICATION)

    while True:
        tempsCourant = datetime.datetime.fromtimestamp(time.time()).strftime('%Y-%m-%d %H:%M:%S')
        position = {"latitude": 48.840689, "longitude": -67.497454}
        valeur = random.randrange(120, 255)

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

    serviceDAO.fermerConnection()
    print("Au revoir !")
