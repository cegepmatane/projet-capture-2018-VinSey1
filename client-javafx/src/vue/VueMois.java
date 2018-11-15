package vue;

import action.Controleur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
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

public class VueMois extends Scene {

    protected StackPane racine;
    protected VBox vboxPrincipal;
    protected ScrollPane scrollPanelisteTest;
    protected Text textNombreTest;
    private LocalDate date = null;

    private Controleur controleur = null;

    public VueMois() {
        super(new StackPane(),800,800);
        racine = (StackPane) this.getRoot();
    }

    public void afficherVueMois()
    {
        vboxPrincipal = new VBox();
        /*****************************************************************/
        //TITRE - Partie du Haut

        HBox hboxTitre = new HBox();
        VBox vboxTitre = new VBox();

        Text titreHaut = new Text("Statistiques de l'application");
        Text titreBas = new Text("Par mois");
        vboxTitre.getChildren().addAll(titreHaut,titreBas);
        hboxTitre.getChildren().add(vboxTitre);
        vboxPrincipal.getChildren().add(hboxTitre);

        HBox centre = new HBox();
        /*****************************************************************/
        //Jour et Nombre de Test - Partie Gauche

        VBox vboxGauche = new VBox();
        vboxGauche.setFillWidth(true);
        HBox mois = new HBox();
        Text textMois = new Text("Mois : ");


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
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
                String strDate = dateFormat.format(dateFormatee);
                System.out.print(strDate);

            }
        });
        //TODO SELECTION DATE


        mois.getChildren().addAll(textMois,selectionDate);
        textNombreTest = new Text("Nombre de test : ");
        //TODO Ajouter valeur
        /*****************************************************************/
        //LISTE VALEURS TODO AJOUTER VALEURS
        scrollPanelisteTest = new ScrollPane();
        vboxGauche.getChildren().addAll(mois,actionAfficherListe,textNombreTest,scrollPanelisteTest);
        /*****************************************************************/
        //Valeur du mois + Synthese des jour et Boutons - Partie Droite

        VBox vboxDroite = new VBox();
        Text valeurMoisTitre = new Text("Valeurs du mois");
        VBox vboxValeursDuMois = new VBox();

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
        vboxValeursDuMois.getChildren().addAll(hboxValeurMoyenne,hboxValeurPlusElevee,hboxValeurPlusFaible);


        Text syntheseJoursTitre = new Text("Synthese des jours");
        VBox vboxSyntheseJours = new VBox();

        HBox hboxJourPlusPollue = new HBox();
        Text jourPlusPollueTitre = new Text("Jour le plus pollue : ");
        Text jourPlusPollueValeur = new Text("10" + "h");
        hboxJourPlusPollue.getChildren().addAll(jourPlusPollueTitre,jourPlusPollueValeur);

        HBox hboxJourMoinsPollue = new HBox();
        Text jourMoinsPollueTitre = new Text("Heure la moins polluee : ");
        Text jourMoinsPollueValeur = new Text("8" + "h");
        hboxJourMoinsPollue.getChildren().addAll(jourMoinsPollueTitre,jourMoinsPollueValeur);

        HBox hboxJourPlusDeTest = new HBox();
        Text jourPlusDeTestTitre = new Text("Heure avec le plus de test : ");
        Text jourPlusDeTestValeur = new Text("15" + "h");
        hboxJourPlusDeTest.getChildren().addAll(jourPlusDeTestTitre,jourPlusDeTestValeur);

        HBox hboxJourMoinsDeTest = new HBox();
        Text jourMoinsDeTestTitre = new Text("Heure avec le moins de tests : ");
        Text jourMoinsDeTestValeur = new Text("16" + "h");
        hboxJourMoinsDeTest.getChildren().addAll(jourMoinsDeTestTitre,jourMoinsDeTestValeur);

        //TODO Ajouter les valeurs
        vboxSyntheseJours.getChildren().addAll(hboxJourPlusPollue,hboxJourMoinsPollue,hboxJourPlusDeTest,hboxJourMoinsDeTest);

        VBox vboxBoutons = new VBox();
        Button actionNaviguerStatistiquesMois = new Button();
        actionNaviguerStatistiquesMois.setText("Statistiques par jour");
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

        vboxDroite.getChildren().addAll(valeurMoisTitre,vboxValeursDuMois,syntheseJoursTitre,vboxSyntheseJours,vboxBoutons);

        centre.getChildren().addAll(vboxGauche,vboxDroite);

        vboxPrincipal.getChildren().add(centre);
        racine.getChildren().add(vboxPrincipal);
        /*****************************************************************/

    } 

    public void setControleur(Controleur controleur) { this.controleur = controleur; }
}
