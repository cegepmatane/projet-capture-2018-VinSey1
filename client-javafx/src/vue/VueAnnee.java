package vue;

import action.Controleur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class VueAnnee extends Scene {

    protected StackPane racine;
    protected VBox vboxPrincipal;
    protected ScrollPane scrollPanelisteTest;
    protected Text textNombreTest;
    private LocalDate date = null;

    private Controleur controleur = null;

    public VueAnnee() {
        super(new StackPane(),800,800);
        racine = (StackPane) this.getRoot();
    }

    public void afficherVueAnnee()
    {
        vboxPrincipal = new VBox();
        /*****************************************************************/
        //TITRE - Partie du Haut

        HBox hboxTitre = new HBox();
        VBox vboxTitre = new VBox();

        Text titreHaut = new Text("Statistiques de l'application");
        Text titreBas = new Text("Par année");
        vboxTitre.getChildren().addAll(titreHaut,titreBas);
        hboxTitre.getChildren().add(vboxTitre);
        vboxPrincipal.getChildren().add(hboxTitre);

        HBox centre = new HBox();
        /*****************************************************************/
        //Annee et Nombre de Test - Partie Gauche

        VBox vboxGauche = new VBox();
        vboxGauche.setFillWidth(true);
        HBox annee = new HBox();
        Text textAnnee = new Text("Année : ");


        DatePicker selectionDate = new DatePicker();
        Button actionAfficherListe = new Button();
        actionAfficherListe.setText("Selectionner date");
        actionAfficherListe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                //ACTION AFFICHER LISTE AVEC DATE
                date = selectionDate.getValue();
                Instant instant = Instant.from(date.atStartOfDay(ZoneId.systemDefault()));
                Date dateFormatee = Date.from(instant);
                DateFormat dateFormat = new SimpleDateFormat("yyyy");
                String strDate = dateFormat.format(dateFormatee);
                System.out.print(strDate);

            }
        });
        //TODO SELECTION DATE


        annee.getChildren().addAll(textAnnee,selectionDate);
        textNombreTest = new Text("Nombre de test : ");
        //TODO Ajouter valeur
        /*****************************************************************/
        //LISTE VALEURS TODO AJOUTER VALEURS
        scrollPanelisteTest = new ScrollPane();
        vboxGauche.getChildren().addAll(annee,actionAfficherListe,textNombreTest,scrollPanelisteTest);
        /*****************************************************************/
        //Valeur de Annee + Synthese des mois et Boutons - Partie Droite

        VBox vboxDroite = new VBox();
        Text valeurAnneeTitre = new Text("Valeurs de l'année");
        VBox vboxValeursDeAnnee = new VBox();

        HBox hboxValeurMoyenne = new HBox();
        Text moyenneValeursTitre = new Text("Moyenne des valeurs : ");
        Text moyenneValeursValeur = new Text("");
        hboxValeurMoyenne.getChildren().addAll(moyenneValeursTitre,moyenneValeursValeur);

        HBox hboxValeurPlusElevee = new HBox();
        Text valeurPlusEleveeTitre = new Text("Valeur la plus elevee : ");
        Text valeurPlusEleveeValeur = new Text("");
        hboxValeurPlusElevee.getChildren().addAll(valeurPlusEleveeTitre,valeurPlusEleveeValeur);

        HBox hboxValeurPlusFaible = new HBox();
        Text valeurPlusFaibleTitre = new Text("Valeur la plus faible : ");
        Text valeurPlusFaibleValeur = new Text("");
        hboxValeurPlusFaible.getChildren().addAll(valeurPlusFaibleTitre,valeurPlusFaibleValeur);
        //TODO Ajouter les valeurs
        vboxValeursDeAnnee.getChildren().addAll(hboxValeurMoyenne,hboxValeurPlusElevee,hboxValeurPlusFaible);


        Text syntheseMoisTitre = new Text("Synthese des mois");
        VBox vboxSyntheseMois = new VBox();

        HBox hboxMoisPlusPollue = new HBox();
        Text moisPlusPollueTitre = new Text("Mois le plus pollue : ");
        Text moisPlusPollueValeur = new Text("10" + "h");
        hboxMoisPlusPollue.getChildren().addAll(moisPlusPollueTitre,moisPlusPollueValeur);

        HBox hboxMoisMoinsPollue = new HBox();
        Text moisMoinsPollueTitre = new Text("Mois la moins polluee : ");
        Text moisMoinsPollueValeur = new Text("8" + "h");
        hboxMoisMoinsPollue.getChildren().addAll(moisMoinsPollueTitre,moisMoinsPollueValeur);

        HBox hboxMoisPlusDeTest = new HBox();
        Text moisPlusDeTestTitre = new Text("Mois avec le plus de test : ");
        Text moisPlusDeTestValeur = new Text("15" + "h");
        hboxMoisPlusDeTest.getChildren().addAll(moisPlusDeTestTitre,moisPlusDeTestValeur);

        HBox hboxMoisMoinsDeTest = new HBox();
        Text moisMoinsDeTestTitre = new Text("Mois avec le moins de tests : ");
        Text moisMoinsDeTestValeur = new Text("16" + "h");
        hboxMoisMoinsDeTest.getChildren().addAll(moisMoinsDeTestTitre,moisMoinsDeTestValeur);

        //TODO Ajouter les valeurs
        vboxSyntheseMois.getChildren().addAll(hboxMoisPlusPollue,hboxMoisMoinsPollue,hboxMoisPlusDeTest,hboxMoisMoinsDeTest);

        VBox vboxBoutons = new VBox();
        Button actionNaviguerStatistiquesMois = new Button();
        actionNaviguerStatistiquesMois.setText("Statistiques par mois");
        actionNaviguerStatistiquesMois.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {

            }
        });
        Button actionNaviguerStatistiquesAnnee = new Button();
        actionNaviguerStatistiquesAnnee.setText("Statistiques par annee");
        actionNaviguerStatistiquesAnnee.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
        vboxBoutons.getChildren().addAll(actionNaviguerStatistiquesMois,actionNaviguerStatistiquesAnnee);

        vboxDroite.getChildren().addAll(valeurAnneeTitre,vboxValeursDeAnnee,syntheseMoisTitre,vboxSyntheseMois,vboxBoutons);

        centre.getChildren().addAll(vboxGauche,vboxDroite);

        vboxPrincipal.getChildren().add(centre);
        racine.getChildren().add(vboxPrincipal);
        /*****************************************************************/

    }

    public void setControleur(Controleur controleur) { this.controleur = controleur; }
}


