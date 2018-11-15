package action;

import accesseur.StatistiqueDAO;
import modele.StatistiqueAnnee;
import modele.StatistiqueJour;
import modele.StatistiqueMois;
import vue.NavigateurDesVues;
import vue.VueAnnee;
import vue.VueJour;
import vue.VueMois;

public class Controleur
{
    private StatistiqueDAO statistiqueDAO = null;
    //VUES
    private VueJour vueJour = null;
    private VueMois vueMois = null;
    private VueAnnee vueAnnee = null;
    /*******************************************************/
    private NavigateurDesVues navigateur;



    private Controleur()
    {
        System.out.println("Initialisation du controleur");
        this.statistiqueDAO = new StatistiqueDAO();

    }

    private static Controleur instance = null;
    public static Controleur getInstance()
    {
        if(null == instance)
        {
            instance = new Controleur();
        }
        return instance;
    }

    public void activerVues(NavigateurDesVues navigateur)
    {
        this.navigateur = navigateur;
        this.vueJour = navigateur.getVueJour();
        this.vueMois = navigateur.getVueMois();
        this.vueAnnee = navigateur.getVueAnnee();

        this.vueJour.afficherVueJour();
        this.navigateur.naviguerVersVueJour();
    }

    public void notifierNaviguerVueJour()
    {
        this.navigateur.naviguerVersVueJour();
    }

    public void notifierNaviguerVueMois()
    {
        this.navigateur.naviguerVersVueMois();
    }


    public void notifierNaviguerVueAnnee()
    {
        this.navigateur.naviguerVersVueAnnee();
    }

    public void notifierChangementDateJour(String annee, String mois, String jour)
    {
        StatistiqueJour statistiqueJour = this.statistiqueDAO.recevoirStatistiqueJour(annee,mois,jour);
        this.vueJour.listerTests(statistiqueJour);
    }

    public void notifierChangementDateMois(String annee, String mois)
    {
        StatistiqueMois statistiqueMois = this.statistiqueDAO.recevoirStatistiqueMois(annee,mois);
        this.vueMois.listerTests(statistiqueMois);
    }

    public void notifierChangementDateAnnee(String annee)
    {
        StatistiqueAnnee statistiqueAnnee = this.statistiqueDAO.recevoirStatistiqueAnnee(annee);
        //System.out.println(statistiqueAnnee.getSynthese().getMaximumAnnee());
        this.vueAnnee.listerTests(statistiqueAnnee);
    }

}
