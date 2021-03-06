package vue;

import action.Controleur;
import javafx.application.Application;
import javafx.stage.Stage;

public class NavigateurDesVues extends Application
{
    private Stage stade;

    private VueJour vueJour = null;
    private  VueMois vueMois = null;
    private VueAnnee vueAnnee = null;

    private Controleur controleur = null;

    public NavigateurDesVues()
    {
        this.vueJour = new VueJour();
        this.vueMois = new VueMois();
        this.vueAnnee = new VueAnnee();
    }

    public void start(Stage stade) throws Exception
    {
        this.stade = stade;
        this.stade.setScene(null);
        this.stade.setTitle("Statistiques");

        this.controleur = Controleur.getInstance();
        this.controleur.activerVues(this);

        this.vueJour.setControleur(controleur);
        this.vueMois.setControleur(controleur);
        this.vueAnnee.setControleur(controleur);
    }

    public VueJour getVueJour()
    {
        return this.vueJour;
    }

    public VueMois getVueMois()
    {
        return this.vueMois;
    }

    public VueAnnee getVueAnnee()
    {
        return this.vueAnnee;
    }

    public void naviguerVersVueJour()
    {
        stade.setScene(this.vueJour);
        stade.show();
    }

    public void naviguerVersVueMois()
    {
        stade.setScene(this.vueMois);
        stade.show();
        this.vueMois.afficherVueMois();
    }

    public void naviguerVersVueAnnee()
    {
        stade.setScene(this.vueAnnee);
        stade.show();
        this.vueAnnee.afficherVueAnnee();
    }


}
