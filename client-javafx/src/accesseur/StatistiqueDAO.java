package accesseur;

import modele.Heure;
import modele.StatistiqueJour;
import modele.Synthese;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
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


    public StatistiqueJour recevoirStatistiqueJour(int annee, int mois, int jour)
    {
        StatistiqueJour stat = new StatistiqueJour();
        ArrayList<Heure> heures = new ArrayList<>();
        Synthese synthese = new Synthese();
        String url = "http://158.69.192.249/pollution/moyenne/annee/"+ annee +"/mois/"+ mois +"/jour/" + jour;
        try {
            File fichierXml = new File(url);
            DocumentBuilderFactory docbuildFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = null;

            docBuilder = docbuildFactory.newDocumentBuilder();

            Document document = docBuilder.parse(fichierXml);

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
                    heure.setHoraire(element.getElementsByTagName("maximum").item(0).getTextContent());
                    heure.setHoraire(element.getElementsByTagName("minimum").item(0).getTextContent());

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
            synthese.setHeureMaxTests(element.getElementsByTagName("heure-valeur-min").item(0).getTextContent());
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
}
