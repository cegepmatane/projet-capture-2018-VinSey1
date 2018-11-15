package action;

import accesseur.StatistiqueDAO;
import modele.StatistiqueJour;
import vue.NavigateurDesVues;
import vue.VueJour;

public class Controleur
{
    private StatistiqueDAO statistiqueDAO = null;
    //VUES
    private VueJour vueJour = null;
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
        this.vueJour.afficherVueJour();
        this.navigateur.naviguerVersVueJour();
    }

    public void notifierNaviguerVueJour()
    {
        this.navigateur.naviguerVersVueJour();
    }

    /*public void notifierNaviguerVueMois()
    {
        this.navigateur.naviguerVersVueMois();
    }
    public void notifierNaviguerVueAnnee()
    {
        this.navigateur.naviguerVersVueAnnee();
    }*/

    public void notifierChangementDate(String annee, String mois, String jour)
    {
        StatistiqueJour statistiqueJour = this.statistiqueDAO.recevoirStatistiqueJour(annee,mois,jour);
        this.vueJour.listerTests(statistiqueJour);
    }

}
