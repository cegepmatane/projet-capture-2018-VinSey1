import json
import http.client

connection = http.client.HTTPConnection("158.69.192.249:8080")
enTetes = {'Content-type': 'application/json'}

contenuEnvoi = {
		"capteur": {
			"pollution": {
    				"date": "2018-10-31 20:07:15",
    				"position": {
  					"latitude": "-34.213",
					"longitude": "52.425"
  				},
 				"valeur": "42"
			}
		}
}
contenuEnvoiJSON = json.dumps(contenuEnvoi)

connection.request("POST", "/", contenuEnvoiJSON, enTetes)

reponse = connection.getresponse()
print(reponse.read().decode())

connection.close()
