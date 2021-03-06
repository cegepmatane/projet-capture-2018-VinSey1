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
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class StatistiqueDAO implements StatistiqueURL {

    private static StatistiqueDAO instance;
    private String dateAujourdhui;
    private int jourActuel, moisActuel, anneeActuel;

    public StatistiqueDAO() {
        dateAujourdhui = LocalDate.now().toString();
        String[] dateAujourdhuiSplit = dateAujourdhui.split("-");
        String jourString = dateAujourdhuiSplit[0] + dateAujourdhuiSplit[1] + dateAujourdhuiSplit[2];
        String moisString = dateAujourdhuiSplit[0] + dateAujourdhuiSplit[1];
        jourActuel = Integer.parseInt(jourString);
        moisActuel = Integer.parseInt(moisString);
        anneeActuel = Integer.parseInt(dateAujourdhuiSplit[0]);
        System.out.println("Date d'aujourdhui: " + jourActuel);
        System.out.println("annee aujoudhui: " + anneeActuel);
    }


    //singleton
    public static StatistiqueDAO getInstance() {
        if (null == instance)
            instance = new StatistiqueDAO();
        return instance;
    }



    public StatistiqueJour recevoirStatistiqueJour(String annee, String mois, String jour) {
        StatistiqueJour stat = new StatistiqueJour();
        ArrayList<Heure> heures = new ArrayList<>();
        Synthese synthese = new Synthese();
        File xml = recevoirXMLJour(annee, mois, jour);
        try {

            DocumentBuilderFactory docbuildFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = null;

            docBuilder = docbuildFactory.newDocumentBuilder();

            Document document = docBuilder.parse(xml);

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


           if((jourActuel - Integer.parseInt(annee + mois + jour)) <= 0)
            {
                supprimerXMLTemporaire(xml);
            }

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

    public StatistiqueMois recevoirStatistiqueMois(String annee, String mois) {
        StatistiqueMois stat = new StatistiqueMois();
        ArrayList<Jour> jours = new ArrayList<>();
        Synthese synthese = new Synthese();

        try {
            File xml = recevoirXMLMois(annee, mois);

            DocumentBuilderFactory docbuildFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = null;
            docBuilder = docbuildFactory.newDocumentBuilder();
            Document document = docBuilder.parse(xml);
            document.getDocumentElement().normalize();
            stat.setMois(document.getElementsByTagName("mois").item(0).getTextContent());
            stat.setNombreTests(document.getElementsByTagName("nombre-tests").item(0).getTextContent());


            NodeList nodeList = document.getElementsByTagName("jour");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
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
            if((moisActuel - Integer.parseInt(annee + mois) <= 0))
                supprimerXMLTemporaire(xml);
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

    public StatistiqueAnnee recevoirStatistiqueAnnee(String annee) {

        StatistiqueAnnee stat = new StatistiqueAnnee();
        ArrayList<Mois> mois = new ArrayList<>();
        Synthese synthese = new Synthese();

        try {
            File xml = recevoirXMLAnnee(annee);
            DocumentBuilderFactory docbuildFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = null;
            docBuilder = docbuildFactory.newDocumentBuilder();
            Document document = docBuilder.parse(xml);
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

            if((anneeActuel - Integer.parseInt(annee)) <= 0)
                supprimerXMLTemporaire(xml);

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

    private File recevoirXMLJour(String annee, String mois, String jour) {
        verifierDossier();
        File XML = new File(System.getProperty("user.home") + "/ClientStatistiqueCapture/" + annee + mois + jour + ".xml");


        if (XML.exists()) {
            return XML;
        } else {
            String urlRecu = JOUR_URL;
            String[] urlSplit = urlRecu.split("-");


            try {
                URL url = new URL(urlSplit[0] + annee + urlSplit[1] + mois + urlSplit[2] + jour);
                DocumentBuilderFactory docbuildFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = null;

                docBuilder = docbuildFactory.newDocumentBuilder();

                Document document = docBuilder.parse(url.openStream());
                File XMLSortie = new File(System.getProperty("user.home") + "/ClientStatistiqueCapture/" + annee + mois + jour + ".xml");

                TransformerFactory tfactory = TransformerFactory.newInstance();
                Transformer xform = tfactory.newTransformer();

                xform.transform(new DOMSource(document), new StreamResult(XMLSortie));

                XML = new File(System.getProperty("user.home") + "/ClientStatistiqueCapture/" + annee + mois + jour + ".xml");

            } catch (SAXException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TransformerConfigurationException e) {
                e.printStackTrace();
            } catch (TransformerException e) {
                e.printStackTrace();
            }


            return XML;
        }
    }

    private File recevoirXMLMois(String annee, String mois) {
        verifierDossier();
        File XML = new File(System.getProperty("user.home") + "/ClientStatistiqueCapture/" + annee + mois + ".xml");
        if (XML.exists()) {
            return XML;
        } else {
            String urlRecu = MOIS_URL;
            String[] urlSplit = urlRecu.split("-");


            try {
                URL url = new URL(urlSplit[0] + annee + urlSplit[1] + mois);
                DocumentBuilderFactory docbuildFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = null;

                docBuilder = docbuildFactory.newDocumentBuilder();

                Document document = docBuilder.parse(url.openStream());
                File XMLSortie = new File(System.getProperty("user.home") + "/ClientStatistiqueCapture/" + annee + mois + ".xml");

                TransformerFactory tfactory = TransformerFactory.newInstance();
                Transformer xform = tfactory.newTransformer();

                xform.transform(new DOMSource(document), new StreamResult(XMLSortie));

                XML = new File(System.getProperty("user.home") + "/ClientStatistiqueCapture/" + annee + mois + ".xml");

            } catch (SAXException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TransformerConfigurationException e) {
                e.printStackTrace();
            } catch (TransformerException e) {
                e.printStackTrace();
            }


            return XML;
        }
    }

    private File recevoirXMLAnnee(String annee) {
        verifierDossier();
        File XML = new File(System.getProperty("user.home") + "/ClientStatistiqueCapture/" + annee + ".xml");
        if (XML.exists()) {
            return XML;
        } else {
            String urlRecu = ANNEE_URL;


            try {
                URL url = new URL(urlRecu + annee);
                DocumentBuilderFactory docbuildFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = null;

                docBuilder = docbuildFactory.newDocumentBuilder();

                Document document = docBuilder.parse(url.openStream());
                File XMLSortie = new File(System.getProperty("user.home") + "/ClientStatistiqueCapture/" + annee + ".xml");

                TransformerFactory tfactory = TransformerFactory.newInstance();
                Transformer xform = tfactory.newTransformer();

                xform.transform(new DOMSource(document), new StreamResult(XMLSortie));

                XML = new File(System.getProperty("user.home") + "/ClientStatistiqueCapture/" + annee + ".xml");

            } catch (SAXException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TransformerConfigurationException e) {
                e.printStackTrace();
            } catch (TransformerException e) {
                e.printStackTrace();
            }


            return XML;
        }
    }

    private void verifierDossier() {
        File path = new File(System.getProperty("user.home") + "/ClientStatistiqueCapture/");
        if (!path.exists())
            path.mkdirs();
    }

    private void supprimerXMLTemporaire(File xml)
    {
        xml.delete();

    }
}