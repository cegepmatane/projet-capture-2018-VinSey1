package vue;

import action.Controleur;
import javafx.application.Application;
import javafx.stage.Stage;

public class NavigateurDesVues extends Application
{
    private Stage stade;

    private VueJour vueJour = null;
    private  VueMois vueMois = null;

    private Controleur controleur = null;

    public NavigateurDesVues()
    {
        this.vueJour = new VueJour();
        this.vueMois = new VueMois();
    }

    public void start(Stage stade) throws Exception
    {
        this.stade = stade;
        this.stade.setScene(null);

        this.controleur = Controleur.getInstance();
        this.controleur.activerVues(this);

        this.vueJour.setControleur(controleur);
        this.vueMois.setControleur(controleur);


    }

    public VueJour getVueJour()
    {
        return this.vueJour;
    }

    public VueMois getVueMois()
    {
        return this.vueMois;
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


}
