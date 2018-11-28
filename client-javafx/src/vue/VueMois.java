package vue;

import action.Controleur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import modele.Jour;
import modele.StatistiqueMois;

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
    protected VBox vboxListeJours;
    protected Text textNombreTestTitre;
    protected Text textNombreTestValeur;
    private LocalDate date = null;

    protected Text moyenneValeursValeur;
    protected Text valeurPlusEleveeValeur;
    protected Text valeurPlusFaibleValeur;
    protected Text jourPlusPollueValeur;
    protected Text jourMoinsPollueValeur;
    protected Text jourPlusDeTestValeur;
    protected Text jourMoinsDeTestValeur;

    private Controleur controleur = null;

    public VueMois() {
        super(new StackPane(),700,350);
        racine = (StackPane) this.getRoot();
    }

    public void afficherVueMois()
    {
        vboxPrincipal = new VBox();
        /*****************************************************************/
        //TITRE - Partie du Haut

        HBox hboxTitre = new HBox();
        VBox vboxTitre = new VBox();
        vboxListeJours = new VBox();

        Text titreHaut = new Text("Statistiques de l'application");
        Text titreBas = new Text("Par mois");
        vboxTitre.getChildren().addAll(titreHaut,titreBas);
        vboxTitre.setAlignment(Pos.CENTER);
        hboxTitre.getChildren().add(vboxTitre);
        hboxTitre.setAlignment(Pos.CENTER);
        hboxTitre.setPadding(new Insets(0, 0, 30, 0));
        vboxPrincipal.getChildren().add(hboxTitre);

        HBox centre = new HBox();
        /*****************************************************************/
        //Mois et Nombre de Test - Partie Gauche

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
                if(selectionDate.getValue() != null)
                {
                    //ACTION AFFICHER LISTE AVEC DATE
                    date = selectionDate.getValue();
                    Instant instant = Instant.from(date.atStartOfDay(ZoneId.systemDefault()));
                    Date dateFormatee = Date.from(instant);
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
                    String strDate = dateFormat.format(dateFormatee);
                    String annee = strDate.split("-")[0];
                    String mois = strDate.split("-")[1];
                    controleur.notifierChangementDateMois(annee, mois);
                }
                else
                {
                    Alert alerte= new Alert(Alert.AlertType.ERROR);
                    alerte.setTitle("Erreur date");
                    alerte.setHeaderText("Veuillez selectionner une date !");
                    alerte.showAndWait();
                    System.out.println("Veuillez selectionner une date !");
                }
            }
        });

        mois.getChildren().addAll(textMois,selectionDate,actionAfficherListe);
        mois.setSpacing(10);
        HBox hboxNombreTest = new HBox();
        textNombreTestTitre = new Text("Nombre de test : ");
        textNombreTestValeur = new Text();
        hboxNombreTest.getChildren().addAll(textNombreTestTitre,textNombreTestValeur);
        hboxNombreTest.setPadding(new Insets(30, 0, 0, 0));

        /*****************************************************************/
        scrollPanelisteTest = new ScrollPane();
        vboxGauche.setPadding(new Insets(0, 20, 0, 0));
        vboxGauche.getChildren().addAll(mois,hboxNombreTest,scrollPanelisteTest);
        /*****************************************************************/
        //Valeur du mois + Synthese des jour et Boutons - Partie Droite

        VBox vboxDroite = new VBox();
        vboxDroite.setPadding(new Insets(10,10,10,10));
        Text valeurMoisTitre = new Text("Valeurs du mois");
        VBox vboxValeursDuMois = new VBox();

        HBox hboxValeurMoyenne = new HBox();
        Text moyenneValeursTitre = new Text("Moyenne des valeurs : ");
        moyenneValeursValeur = new Text("");
        hboxValeurMoyenne.getChildren().addAll(moyenneValeursTitre,moyenneValeursValeur);

        HBox hboxValeurPlusElevee = new HBox();
        Text valeurPlusEleveeTitre = new Text("Valeur la plus elevee : ");
        valeurPlusEleveeValeur = new Text("");
        hboxValeurPlusElevee.getChildren().addAll(valeurPlusEleveeTitre,valeurPlusEleveeValeur);

        HBox hboxValeurPlusFaible = new HBox();
        Text valeurPlusFaibleTitre = new Text("Valeur la plus faible : ");
        valeurPlusFaibleValeur = new Text("");
        hboxValeurPlusFaible.getChildren().addAll(valeurPlusFaibleTitre,valeurPlusFaibleValeur);
        vboxValeursDuMois.getChildren().addAll(hboxValeurMoyenne,hboxValeurPlusElevee,hboxValeurPlusFaible);


        Text syntheseJoursTitre = new Text("Synthese des jours");
        VBox vboxSyntheseJours = new VBox();

        HBox hboxJourPlusPollue = new HBox();
        Text jourPlusPollueTitre = new Text("Jour le plus pollue : ");
        jourPlusPollueValeur = new Text("");
        hboxJourPlusPollue.getChildren().addAll(jourPlusPollueTitre,jourPlusPollueValeur);

        HBox hboxJourMoinsPollue = new HBox();
        Text jourMoinsPollueTitre = new Text("Jour la moins polluee : ");
        jourMoinsPollueValeur = new Text("");
        hboxJourMoinsPollue.getChildren().addAll(jourMoinsPollueTitre,jourMoinsPollueValeur);

        HBox hboxJourPlusDeTest = new HBox();
        Text jourPlusDeTestTitre = new Text("Jour avec le plus de test : ");
        jourPlusDeTestValeur = new Text("");
        hboxJourPlusDeTest.getChildren().addAll(jourPlusDeTestTitre,jourPlusDeTestValeur);

        HBox hboxJourMoinsDeTest = new HBox();
        Text jourMoinsDeTestTitre = new Text("Jour avec le moins de tests : ");
        jourMoinsDeTestValeur = new Text("");
        hboxJourMoinsDeTest.getChildren().addAll(jourMoinsDeTestTitre,jourMoinsDeTestValeur);

        vboxSyntheseJours.getChildren().addAll(hboxJourPlusPollue,hboxJourMoinsPollue,hboxJourPlusDeTest,hboxJourMoinsDeTest);

        VBox vboxBoutons = new VBox();
        Button actionNaviguerStatistiquesJour = new Button();
        actionNaviguerStatistiquesJour.setText("Statistiques par jour");
        actionNaviguerStatistiquesJour.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                controleur.notifierNaviguerVueJour();
            }
        });
        Button actionNaviguerStatistiquesAnnee = new Button();
        actionNaviguerStatistiquesAnnee.setText("Statistiques par annee");
        actionNaviguerStatistiquesAnnee.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                controleur.notifierNaviguerVueAnnee();
            }
        });
        vboxBoutons.getChildren().addAll(actionNaviguerStatistiquesJour,actionNaviguerStatistiquesAnnee);

        vboxDroite.getChildren().addAll(valeurMoisTitre,vboxValeursDuMois,syntheseJoursTitre,vboxSyntheseJours,vboxBoutons);

        centre.getChildren().addAll(vboxGauche,vboxDroite);

        vboxBoutons.setSpacing(3);
        vboxDroite.setSpacing(3);

        vboxPrincipal.getChildren().add(centre);
        vboxPrincipal.setPadding(new Insets(10,10,10,10));
        racine.getChildren().add(vboxPrincipal);
        /*****************************************************************/

    }

    public void listerTests(StatistiqueMois statistiqueMois)
    {
        vboxListeJours.getChildren().clear();

        textNombreTestValeur.setText(statistiqueMois.getNombreTests());
        moyenneValeursValeur.setText(statistiqueMois.getSynthese().getMoyenneMois());

        valeurPlusEleveeValeur.setText(statistiqueMois.getSynthese().getMaximumMois());
        valeurPlusFaibleValeur.setText(statistiqueMois.getSynthese().getMinimumMois());

        jourPlusPollueValeur.setText(statistiqueMois.getSynthese().getJourValeurMax());
        jourMoinsPollueValeur.setText(statistiqueMois.getSynthese().getJourValeurMin());

        jourPlusDeTestValeur.setText(statistiqueMois.getSynthese().getJourMaxTest());
        jourMoinsDeTestValeur.setText(statistiqueMois.getSynthese().getJourMinTest());

        for (Jour jour:statistiqueMois.getJours())
        {
            HBox hboxPrincipalJour = new HBox();

            VBox vboxJour = new VBox();
            Text textJour = new Text("Jour");
            Text textValeurJour = new Text(jour.getDate());
            vboxJour.getChildren().addAll(textJour, textValeurJour);
            vboxJour.setAlignment(Pos.CENTER);

            VBox vBoxValeursJour = new VBox();
            Text textTitreValeurs = new Text("Valeurs (en g/m3)");

            HBox hboxPrincipalValeurs = new HBox();

            VBox vboxMoyenneJour = new VBox();
            Text textMoyenneTitre = new Text("Moyenne");
            Text textMoyenneValeur = new Text(jour.getMoyenne());
            vboxMoyenneJour.getChildren().addAll(textMoyenneTitre, textMoyenneValeur);

            VBox vboxMaximumJour = new VBox();
            Text textMaximumTitre = new Text("Maximum");
            Text textMaximumValeur = new Text(jour.getMaximum());
            vboxMaximumJour.getChildren().addAll(textMaximumTitre, textMaximumValeur);

            VBox vboxMinimumJour = new VBox();
            Text textMinimumTitre = new Text("Minimum");
            Text textMinimumValeur = new Text(jour.getMinimum());
            vboxMinimumJour.getChildren().addAll(textMinimumTitre, textMinimumValeur);

            hboxPrincipalValeurs.getChildren().addAll(vboxMoyenneJour, vboxMaximumJour, vboxMinimumJour);
            hboxPrincipalValeurs.setSpacing(20);

            vBoxValeursJour.getChildren().addAll(textTitreValeurs, hboxPrincipalValeurs);
            vBoxValeursJour.setAlignment(Pos.CENTER);

            hboxPrincipalJour.setSpacing(20);
            hboxPrincipalJour.setPadding(new Insets(0,0,10,0));
            hboxPrincipalJour.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                    + "-fx-border-width: 1;" + "-fx-border-insets: 5;"
                    + "-fx-border-radius: 5;" + "-fx-border-color: blue;");
            hboxPrincipalJour.getChildren().addAll(vboxJour, vBoxValeursJour);
            vboxListeJours.getChildren().add(hboxPrincipalJour);
            scrollPanelisteTest.setContent(vboxListeJours);
            scrollPanelisteTest.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        }

    }

    public void setControleur(Controleur controleur)
    {
        this.controleur = controleur;
    }
}
