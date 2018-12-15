import com.google.cloud.Timestamp;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;

import donnee.GoogleDAO;

public class App {

	public static void main(String[] args) {
		
		GoogleDAO accesseur = new GoogleDAO();
		
		accesseur.ajouterCapteur(Timestamp.now(), 4, -40, 70);
		
	}
}