import com.google.cloud.Timestamp;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;

import donnee.GoogleDAO;

public class App {

	public static void main(String[] args) {
		
		GoogleDAO accesseur = new GoogleDAO();
		
		accesseur.ajouterCapteur(Timestamp.now(), 4, -40, 70);
		
		Key idCapteur = accesseur.ajouterCapteurEtRecupererId(Timestamp.now(), 45, -78, 3);
		
		Entity capteurTrouve = accesseur.trouverCapteurParId(idCapteur);
		
		System.out.println("Capteur " + capteurTrouve.getKey().getId() + " : " +
				"\n date : " + capteurTrouve.getTimestamp("date") + 
				"\n latitude : " + capteurTrouve.getDouble("latitude") + 
				"\n longitude : " + capteurTrouve.getDouble("longitude") +
				"\n valeur : " + capteurTrouve.getDouble("valeur"));
		
	}
}