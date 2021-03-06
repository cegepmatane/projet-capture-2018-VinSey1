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
import modele.Mois;
import modele.StatistiqueAnnee;

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
    protected VBox vboxListeMois;
    protected Text textNombreTestValeur;
    private LocalDate date = null;

    protected Text moyenneValeursValeur;
    protected Text valeurPlusEleveeValeur;
    protected Text valeurPlusFaibleValeur;
    protected Text moisPlusPollueValeur;
    protected Text moisMoinsPollueValeur;
    protected Text moisPlusDeTestValeur;
    protected Text moisMoinsDeTestValeur;

    private Controleur controleur = null;

    public VueAnnee() {
        super(new StackPane(),700,350);
        racine = (StackPane) this.getRoot();
    }

    public void afficherVueAnnee()
    {
        racine.getChildren().clear();
        vboxPrincipal = new VBox();
        /*****************************************************************/
        //TITRE - Partie du Haut

        HBox hboxTitre = new HBox();
        VBox vboxTitre = new VBox();
        vboxListeMois = new VBox();

        Text titreHaut = new Text("Statistiques de l'application");
        Text titreBas = new Text("Par année");
        vboxTitre.getChildren().addAll(titreHaut,titreBas);
        vboxTitre.setAlignment(Pos.CENTER);
        hboxTitre.getChildren().add(vboxTitre);
        hboxTitre.setAlignment(Pos.CENTER);
        hboxTitre.setPadding(new Insets(0, 0, 30, 0));
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
                if(selectionDate.getValue() != null)
                {
                    //ACTION AFFICHER LISTE AVEC DATE
                    date = selectionDate.getValue();
                    Instant instant = Instant.from(date.atStartOfDay(ZoneId.systemDefault()));
                    Date dateFormatee = Date.from(instant);
                    DateFormat dateFormat = new SimpleDateFormat("yyyy");
                    String strDate = dateFormat.format(dateFormatee);
                    System.out.println(strDate);
                    controleur.notifierChangementDateAnnee(strDate);
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

        annee.getChildren().addAll(textAnnee,selectionDate,actionAfficherListe);
        annee.setSpacing(10);
        HBox hboxNombreTest = new HBox();
        Text textNombreTestTitre = new Text("Nombre de test : ");
        textNombreTestValeur = new Text();
        hboxNombreTest.getChildren().addAll(textNombreTestTitre,textNombreTestValeur);
        hboxNombreTest.setPadding(new Insets(30, 0, 0, 0));

        /*****************************************************************/
        //LISTE VALEURS
        scrollPanelisteTest = new ScrollPane();
        vboxGauche.setPadding(new Insets(0, 20, 0, 0));
        vboxGauche.getChildren().addAll(annee,hboxNombreTest,scrollPanelisteTest);
        /*****************************************************************/
        //Valeur de Annee + Synthese des mois et Boutons - Partie Droite

        VBox vboxDroite = new VBox();
        vboxDroite.setPadding(new Insets(10,10,10,10));
        Text valeurAnneeTitre = new Text("Valeurs de l'année");
        VBox vboxValeursDeAnnee = new VBox();

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

        vboxValeursDeAnnee.getChildren().addAll(hboxValeurMoyenne,hboxValeurPlusElevee,hboxValeurPlusFaible);


        Text syntheseMoisTitre = new Text("Synthese des mois");
        VBox vboxSyntheseMois = new VBox();

        HBox hboxMoisPlusPollue = new HBox();
        Text moisPlusPollueTitre = new Text("Mois le plus pollue : ");
        moisPlusPollueValeur = new Text("");
        hboxMoisPlusPollue.getChildren().addAll(moisPlusPollueTitre,moisPlusPollueValeur);

        HBox hboxMoisMoinsPollue = new HBox();
        Text moisMoinsPollueTitre = new Text("Mois la moins polluee : ");
        moisMoinsPollueValeur = new Text("");
        hboxMoisMoinsPollue.getChildren().addAll(moisMoinsPollueTitre,moisMoinsPollueValeur);

        HBox hboxMoisPlusDeTest = new HBox();
        Text moisPlusDeTestTitre = new Text("Mois avec le plus de test : ");
        moisPlusDeTestValeur = new Text("");
        hboxMoisPlusDeTest.getChildren().addAll(moisPlusDeTestTitre,moisPlusDeTestValeur);

        HBox hboxMoisMoinsDeTest = new HBox();
        Text moisMoinsDeTestTitre = new Text("Mois avec le moins de tests : ");
        moisMoinsDeTestValeur = new Text("");
        hboxMoisMoinsDeTest.getChildren().addAll(moisMoinsDeTestTitre,moisMoinsDeTestValeur);

        vboxSyntheseMois.getChildren().addAll(hboxMoisPlusPollue,hboxMoisMoinsPollue,hboxMoisPlusDeTest,hboxMoisMoinsDeTest);

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
        Button actionNaviguerStatistiquesMois = new Button();
        actionNaviguerStatistiquesMois.setText("Statistiques par mois");
        actionNaviguerStatistiquesMois.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                controleur.notifierNaviguerVueMois();
            }
        });
        vboxBoutons.getChildren().addAll(actionNaviguerStatistiquesJour,actionNaviguerStatistiquesMois);

        vboxDroite.getChildren().addAll(valeurAnneeTitre,vboxValeursDeAnnee,syntheseMoisTitre,vboxSyntheseMois,vboxBoutons);

        centre.getChildren().addAll(vboxGauche,vboxDroite);

        vboxBoutons.setSpacing(3);
        vboxDroite.setSpacing(3);

        vboxPrincipal.getChildren().add(centre);
        vboxPrincipal.setPadding(new Insets(10,10,10,10));
        racine.getChildren().add(vboxPrincipal);
        /*****************************************************************/

    }

    public void listerTests(StatistiqueAnnee statistiqueAnnee)
    {
        vboxListeMois.getChildren().clear();

        textNombreTestValeur.setText(statistiqueAnnee.getNombreTests());
        moyenneValeursValeur.setText(statistiqueAnnee.getSynthese().getMoyenneAnnee());

        valeurPlusEleveeValeur.setText(statistiqueAnnee.getSynthese().getMaximumAnnee());
        valeurPlusFaibleValeur.setText(statistiqueAnnee.getSynthese().getMinimumAnnee());

        moisPlusPollueValeur.setText(statistiqueAnnee.getSynthese().getMoisValeurMax());
        moisMoinsPollueValeur.setText(statistiqueAnnee.getSynthese().getMoisValeurMin());

        moisPlusDeTestValeur.setText(statistiqueAnnee.getSynthese().getMoisMaxTests());
        moisMoinsDeTestValeur.setText(statistiqueAnnee.getSynthese().getMoisMinTests());

        for (Mois mois:statistiqueAnnee.getMois())
        {
            HBox hboxPrincipalMois= new HBox();

            VBox vboxMois = new VBox();
            Text textMois = new Text("Mois");
            Text textValeurMois = new Text(mois.getDate());
            vboxMois.getChildren().addAll(textMois,textValeurMois);
            vboxMois.setAlignment(Pos.CENTER);

            VBox vBoxValeursMois = new VBox();
            Text textTitreValeurs = new Text("Valeurs (en g/m3)");

            HBox hboxPrincipalValeurs = new HBox();
            hboxPrincipalValeurs.setSpacing(20);

            VBox vboxMoyenneMois = new VBox();
            Text textMoyenneTitre = new Text("Moyenne");
            Text textMoyenneValeur = new Text(mois.getMoyenne());
            vboxMoyenneMois.getChildren().addAll(textMoyenneTitre,textMoyenneValeur);

            VBox vboxMaximumMois = new VBox();
            Text textMaximumTitre = new Text("Maximum");
            Text textMaximumValeur = new Text(mois.getMaximum());
            vboxMaximumMois.getChildren().addAll(textMaximumTitre,textMaximumValeur);

            VBox vboxMinimumMois = new VBox();
            Text textMinimumTitre = new Text("Minimum");
            Text textMinimumValeur = new Text(mois.getMinimum());
            vboxMinimumMois.getChildren().addAll(textMinimumTitre,textMinimumValeur);

            hboxPrincipalValeurs.getChildren().addAll(vboxMoyenneMois,vboxMaximumMois,vboxMinimumMois);

            vBoxValeursMois.getChildren().addAll(textTitreValeurs,hboxPrincipalValeurs);
            vBoxValeursMois.setAlignment(Pos.CENTER);
            hboxPrincipalMois.setSpacing(20);
            hboxPrincipalMois.setPadding(new Insets(0,0,10,0));

            hboxPrincipalMois.getChildren().addAll(vboxMois,vBoxValeursMois);
            hboxPrincipalMois.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                    + "-fx-border-width: 1;" + "-fx-border-insets: 5;"
                    + "-fx-border-radius: 5;" + "-fx-border-color: blue;");
            vboxListeMois.getChildren().add(hboxPrincipalMois);
            scrollPanelisteTest.setContent(vboxListeMois);
            scrollPanelisteTest.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

            //System.out.println(heure.getHoraire());

        }

    }

    public void setControleur(Controleur controleur) { this.controleur = controleur; }
}


