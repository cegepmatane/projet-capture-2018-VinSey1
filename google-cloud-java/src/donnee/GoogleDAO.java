package donnee;

import com.google.cloud.Timestamp;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;

public class GoogleDAO {
	
	private final Datastore baseDeDonnees;
	private final KeyFactory generateurIdCapteur;

	public GoogleDAO() {
		
		baseDeDonnees = DatastoreOptions.newBuilder().setProjectId("projet-capture-225623").build().getService();
		generateurIdCapteur = baseDeDonnees.newKeyFactory().setKind("capture");
		
	}
	
	public void ajouterCapteur(Timestamp date, float latitude, float longitude, float valeur) {
		
		Key id = baseDeDonnees.allocateId(generateurIdCapteur.newKey());
		
		Entity nouveauCapteur = Entity.newBuilder(id)
				.set("date", date)
				.set("latitude", latitude)
				.set("longitude", longitude)
				.set("valeur", valeur)
		.build();
		
		baseDeDonnees.put(nouveauCapteur);
		
	}
}