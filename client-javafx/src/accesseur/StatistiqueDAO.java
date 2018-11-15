package accesseur;

import modele.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class StatistiqueDAO {

    private static StatistiqueDAO instance;

    public StatistiqueDAO()
    {

    }


    //singleton
    public static StatistiqueDAO getInstance()
    {
        if(null == instance)
            instance = new StatistiqueDAO();
        return instance;
    }


    public StatistiqueJour recevoirStatistiqueJour(String annee, String mois, String jour)
    {
        StatistiqueJour stat = new StatistiqueJour();
        ArrayList<Heure> heures = new ArrayList<>();
        Synthese synthese = new Synthese();
        try {
            URL url = new URL("http://158.69.192.249/pollution/moyenne/annee/"+ annee +"/mois/"+ mois +"/jour/" + jour);
            DocumentBuilderFactory docbuildFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = null;

            docBuilder = docbuildFactory.newDocumentBuilder();

            Document document = docBuilder.parse(url.openStream());

            document.getDocumentElement().normalize();

            stat.setJour(document.getElementsByTagName("jour").item(0).getTextContent());
            stat.setNombreDeTest(document.getElementsByTagName("nombre-tests").item(0).getTextContent());

            NodeList nodeList = document.getElementsByTagName("heure");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    Heure heure = new Heure();
                    heure.setHoraire(element.getElementsByTagName("horaire").item(0).getTextContent());
                    heure.setMoyenne(element.getElementsByTagName("moyenne").item(0).getTextContent());
                    heure.setMaximum(element.getElementsByTagName("maximum").item(0).getTextContent());
                    heure.setMinimum(element.getElementsByTagName("minimum").item(0).getTextContent());

                    heures.add(heure);
                }
            }
            Node node = document.getElementsByTagName("synthese").item(0);
            Element element = (Element) node;
            synthese.setMoyenneJour(element.getElementsByTagName("moyenne-jour").item(0).getTextContent());
            synthese.setMaximumJour(element.getElementsByTagName("maximum-jour").item(0).getTextContent());
            synthese.setMinimumJour(element.getElementsByTagName("minimum-jour").item(0).getTextContent());
            synthese.setHeureValeurMax(element.getElementsByTagName("heure-valeur-max").item(0).getTextContent());
            synthese.setHeureValeurMin(element.getElementsByTagName("heure-valeur-min").item(0).getTextContent());
            synthese.setHeureMaxTests(element.getElementsByTagName("heure-max-tests").item(0).getTextContent());
            synthese.setHeureMinTests(element.getElementsByTagName("heure-min-tests").item(0).getTextContent());

            stat.setHeures(heures);
            stat.setSynthese(synthese);

            return stat;

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


       return null;


    }

    public StatistiqueMois recevoirStatistiqueMois(String annee, String mois)
    {
        StatistiqueMois stat = new StatistiqueMois();
        ArrayList<Jour> jours = new ArrayList<>();
        Synthese synthese = new Synthese();
        //URL url = new URL("http://158.69.192.249/pollution/moyenne/annee/"+ annee +"/mois/"+ mois);

        try
        {
            URL url = new URL("http://158.69.192.249/pollution/moyenne/annee/"+ annee +"/mois/"+ mois);

            DocumentBuilderFactory docbuildFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = null;
            docBuilder = docbuildFactory.newDocumentBuilder();
            Document document = docBuilder.parse(url.openStream());
            document.getDocumentElement().normalize();
            stat.setMois(document.getElementsByTagName("mois").item(0).getTextContent());
            stat.setNombreTests(document.getElementsByTagName("nombre-tests").item(0).getTextContent());


            NodeList nodeList = document.getElementsByTagName("jour");

            for (int i = 0; i < nodeList.getLength(); i++)
            {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element) node;
                    Jour jour = new Jour();
                    jour.setDate(element.getElementsByTagName("date").item(0).getTextContent());
                    jour.setMoyenne(element.getElementsByTagName("moyenne").item(0).getTextContent());
                    jour.setMaximum(element.getElementsByTagName("maximum").item(0).getTextContent());
                    jour.setMinimum(element.getElementsByTagName("minimum").item(0).getTextContent());

                    jours.add(jour);
                }
            }
                Node node = document.getElementsByTagName("synthese").item(0);
                Element element = (Element) node;
                synthese.setMoyenneMois(element.getElementsByTagName("moyenne-mois").item(0).getTextContent());
                synthese.setMaximumMois(element.getElementsByTagName("maximum-mois").item(0).getTextContent());
                synthese.setMinimumMois(element.getElementsByTagName("minimum-mois").item(0).getTextContent());
                synthese.setJourValeurMax(element.getElementsByTagName("jour-valeur-max").item(0).getTextContent());
                synthese.setJourValeurMin(element.getElementsByTagName("jour-valeur-min").item(0).getTextContent());
                synthese.setJourMaxTest(element.getElementsByTagName("jour-max-test").item(0).getTextContent());
                synthese.setJourMinTest(element.getElementsByTagName("jour-min-test").item(0).getTextContent());

                stat.setJours(jours);
                stat.setSynthese(synthese);
                return stat;

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public StatistiqueAnnee recevoirStatistiqueAnnee(String annee)
    {

        StatistiqueAnnee stat = new StatistiqueAnnee();
        ArrayList<Mois> mois = new ArrayList<>();
        Synthese synthese = new Synthese();

        try
        {
            URL url = new URL("http://158.69.192.249/pollution/moyenne/annee/"+ annee);
            DocumentBuilderFactory docbuildFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = null;
            docBuilder = docbuildFactory.newDocumentBuilder();
            Document document = docBuilder.parse(url.openStream());
            document.getDocumentElement().normalize();
            stat.setAnnee(document.getElementsByTagName("annee").item(0).getTextContent());
            stat.setNombreTests(document.getElementsByTagName("nombre-tests").item(0).getTextContent());

            NodeList nodeList = document.getElementsByTagName("mois");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    Mois moi = new Mois();
                    moi.setDate(element.getElementsByTagName("date").item(0).getTextContent());
                    moi.setMoyenne(element.getElementsByTagName("moyenne").item(0).getTextContent());
                    moi.setMaximum(element.getElementsByTagName("maximum").item(0).getTextContent());
                    moi.setMinimum(element.getElementsByTagName("minimum").item(0).getTextContent());

                    mois.add(moi);
                }
            }

            Node node = document.getElementsByTagName("synthese").item(0);
            Element element = (Element) node;
            synthese.setMoyenneAnnee(element.getElementsByTagName("moyenne-annee").item(0).getTextContent());
            synthese.setMaximumAnnee(element.getElementsByTagName("maximum-annee").item(0).getTextContent());
            synthese.setMinimumAnnee(element.getElementsByTagName("minimum-annee").item(0).getTextContent());
            synthese.setMoisValeurMax(element.getElementsByTagName("mois-valeur-max").item(0).getTextContent());
            synthese.setMoisValeurMin(element.getElementsByTagName("mois-valeur-min").item(0).getTextContent());
            synthese.setMoisMaxTests(element.getElementsByTagName("mois-max-tests").item(0).getTextContent());
            synthese.setMoisMinTests(element.getElementsByTagName("mois-min-tests").item(0).getTextContent());

            stat.setMois(mois);
            stat.setSynthese(synthese);
            return stat;

    } catch (ParserConfigurationException e) {
        e.printStackTrace();
    } catch (SAXException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
        return null;
    }


}
