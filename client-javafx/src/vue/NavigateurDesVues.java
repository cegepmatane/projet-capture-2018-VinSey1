package vue;

import action.Controleur;
import javafx.application.Application;
import javafx.stage.Stage;

public class NavigateurDesVues extends Application
{
    private Stage stade;

    private VueJour vueJour = null;

    private Controleur controleur = null;

    public NavigateurDesVues()
    {
        this.vueJour = new VueJour();
    }

    public void start(Stage stade) throws Exception
    {
        this.stade = stade;
        this.stade.setScene(null);

        this.controleur = Controleur.getInstance();
        this.controleur.activerVues(this);
        this.vueJour.setControleur(controleur);


    }

    public VueJour getVueJour()
    {
        return this.vueJour;
    }

    public void naviguerVersVueJour()
    {
        stade.setScene(this.vueJour);
        stade.show();
    }


}
