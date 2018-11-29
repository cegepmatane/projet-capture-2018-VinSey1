package ca.qc.cgmatane.informatique.projet_capture_2018_vinsey1.donnee;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import ca.qc.cgmatane.informatique.projet_capture_2018_vinsey1.modele.Heure;

public class CapteurDAO {

    private static CapteurDAO instance = null;
    private ServiceDAO accesseurService;
    private String xml, jour, nombreTests, moyenneJour, maximumJour, minimumJour, heureValeurMax, heureValeurMin, heureMaximumTests, heureMinimumTests;
    private List<Heure> listeHeures;

    public static CapteurDAO getInstance(){
        if(null == instance){
            instance = new CapteurDAO();
        }
        return instance;
    }

    public CapteurDAO() {
        accesseurService = new ServiceDAO();
        String date = new SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis());
        String[] dateCoupee = date.split("/");
        jour = date;
        try {
            //Requete avec le jour du lancement de l'application
            xml = accesseurService.execute("http://158.69.192.249/pollution/moyenne/annee/"+dateCoupee[2]+"/mois/"+dateCoupee[1]+"/jour/"+dateCoupee[0], "</statistiques-jour>").get();
            //Requete pour avoir des valeurs
            //xml = accesseurService.execute("http://158.69.192.249/pollution/moyenne/annee/2032/mois/04/jour/20", "</statistiques-jour>").get();
            //FAIRE SECURISATION
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            initialiserStatistiques();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    private void initialiserStatistiques() throws ParserConfigurationException, IOException, SAXException {
        listeHeures = new ArrayList<>();
        DocumentBuilder parseur = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = parseur.parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
        NodeList listeNoeudHeures = document.getElementsByTagName("heure");
        for(int iterateur = 0; iterateur < listeNoeudHeures.getLength(); iterateur++){
            Element noeudHeure = (Element) listeNoeudHeures.item(iterateur);

            String horaire = noeudHeure.getElementsByTagName("horaire").item(0).getTextContent();
            String moyenne = noeudHeure.getElementsByTagName("moyenne").item(0).getTextContent();
            String maximum = noeudHeure.getElementsByTagName("maximum").item(0).getTextContent();
            String minimum = noeudHeure.getElementsByTagName("minimum").item(0).getTextContent();

            Heure heure = new Heure(horaire, moyenne, maximum, minimum);

            listeHeures.add(heure);
        }

        moyenneJour = document.getElementsByTagName("moyenne-jour").item(0).getTextContent();
        maximumJour = document.getElementsByTagName("maximum-jour").item(0).getTextContent();
        minimumJour = document.getElementsByTagName("minimum-jour").item(0).getTextContent();
        heureValeurMax = document.getElementsByTagName("heure-valeur-max").item(0).getTextContent();
        heureValeurMin = document.getElementsByTagName("heure-valeur-min").item(0).getTextContent();
        heureMaximumTests = document.getElementsByTagName("heure-max-tests").item(0).getTextContent();
        heureMinimumTests = document.getElementsByTagName("heure-min-tests").item(0).getTextContent();
        nombreTests = document.getElementsByTagName("nombre-tests").item(0).getTextContent();
    }

    public List<HashMap<String,String>> recupererListeHeuresPourAdapteur() {
        List<HashMap<String, String>> listeHeuresPourAdaptateur = new ArrayList<HashMap<String, String>>();
        for(Heure heure : listeHeures){
            listeHeuresPourAdaptateur.add(heure.obtenirHeurePourAdapteur());
        }
        return listeHeuresPourAdaptateur;
    }

    public boolean capteurEstActif(){
        accesseurService = new ServiceDAO();
        Document document = null;
        try {
            xml = accesseurService.execute("http://158.69.192.249/pollution/alerte/", "</surveillance>").get();
            DocumentBuilder parseur = null;
            parseur = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = parseur.parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        int valeurCapteurActif = Integer.parseInt(document.getElementsByTagName("actif").item(0).getTextContent());

        boolean capteurActif = (valeurCapteurActif != 0);
        return capteurActif;
    }

    public List<Heure> getListeHeures(){
        return listeHeures;
    }

    public String getJour() {
        return jour;
    }

    public String getNombreTests() {
        return nombreTests;
    }

    public String getMoyenneJour() {
        return moyenneJour;
    }

    public String getMaximumJour() {
        return maximumJour;
    }

    public String getMinimumJour() {
        return minimumJour;
    }

    public String getHeureValeurMax() {
        return heureValeurMax;
    }

    public String getHeureValeurMin() {
        return heureValeurMin;
    }

    public String getHeureMaximumTests() {
        return heureMaximumTests;
    }

    public String getHeureMinimumTests() {
        return heureMinimumTests;
    }
}