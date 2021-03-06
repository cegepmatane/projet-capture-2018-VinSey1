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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import modele.Heure;
import modele.StatistiqueJour;
import org.omg.PortableServer.POA;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class VueJour extends Scene
{
    protected StackPane racine;
    protected VBox vboxPrincipal;
    protected ScrollPane scrollPanelisteTest;
    protected VBox vboxListeHeures;
    protected Text textNombreTestValeur;

    protected Text moyenneValeursValeur;
    protected Text valeurPlusEleveeValeur;
    protected Text valeurPlusFaibleValeur;
    protected Text heurePlusPollueeValeur;
    protected Text heureMoinsPollueeValeur;
    protected Text heurePlusDeTestValeur;
    protected Text heureMoinsDeTestValeur;

    private LocalDate date = null;

    private Controleur controleur = null;


    public VueJour()
    {
        super(new StackPane(),700,350);
        racine = (StackPane) this.getRoot();
    }

    public void afficherVueJour()
    {
        racine.getChildren().clear();
        vboxPrincipal = new VBox();
        /*****************************************************************/
        //TITRE - Partie du Haut

        HBox hboxTitre = new HBox();
        VBox vboxTitre = new VBox();
        vboxListeHeures = new VBox();

        Text titreHaut = new Text("Statistiques de l'application");
        Text titreBas = new Text("Par jour");
        vboxTitre.getChildren().addAll(titreHaut,titreBas);
        vboxTitre.setAlignment(Pos.CENTER);
        hboxTitre.getChildren().add(vboxTitre);
        hboxTitre.setAlignment(Pos.CENTER);
        vboxPrincipal.getChildren().add(hboxTitre);
        hboxTitre.setPadding(new Insets(0, 0, 30, 0));


        HBox centre = new HBox();
        /*****************************************************************/
        //Jour et Nombre de Test - Partie Gauche

        VBox vboxGauche = new VBox();
        vboxGauche.setFillWidth(true);
        HBox jour = new HBox();
        Text textJour = new Text("Jour : ");



        DatePicker selectionDate = new DatePicker();
        Button actionAfficherListe = new Button();
        actionAfficherListe.setText("Selectionner date");
        actionAfficherListe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                if(selectionDate.getValue() != null) {
                    //ACTION AFFICHER LISTE AVEC DATE
                    date = selectionDate.getValue();
                    Instant instant = Instant.from(date.atStartOfDay(ZoneId.systemDefault()));
                    Date dateFormatee = Date.from(instant);
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String strDate = dateFormat.format(dateFormatee);
                    String annee = strDate.split("-")[0];
                    String mois = strDate.split("-")[1];
                    String jour = strDate.split("-")[2];
                    controleur.notifierChangementDateJour(annee, mois, jour);
                    //controleur.notifierChangementDate("2032","04","20");
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

        jour.getChildren().addAll(textJour,selectionDate,actionAfficherListe);
        jour.setSpacing(10);
        HBox hboxNombreTest = new HBox();
        Text textNombreTestTitre = new Text("Nombre de test : ");
        textNombreTestValeur = new Text();
        hboxNombreTest.getChildren().addAll(textNombreTestTitre,textNombreTestValeur);
        hboxNombreTest.setPadding(new Insets(30, 0, 0, 0));

        /*****************************************************************/
        //LISTE VALEURS
        scrollPanelisteTest = new ScrollPane();
        vboxGauche.setPadding(new Insets(0, 20, 0, 0));
        vboxGauche.getChildren().addAll(jour,hboxNombreTest,scrollPanelisteTest);
        /*****************************************************************/
        //Valeur du jour + Synthese des heures et Boutons - Partie Droite

        VBox vboxDroite = new VBox();
        Text valeurJourTitre = new Text("Valeurs du jour");
        VBox vboxValeursDujour = new VBox();

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

        vboxValeursDujour.getChildren().addAll(hboxValeurMoyenne,hboxValeurPlusElevee,hboxValeurPlusFaible);


        Text syntheseHeuresTitre = new Text("Synthese des heures");
        VBox vboxSyntheseHeures = new VBox();

        HBox hboxHeurePlusPolluee = new HBox();
        Text heurePlusPollueeTitre = new Text("Heure la plus polluee : ");
        heurePlusPollueeValeur = new Text("");
        hboxHeurePlusPolluee.getChildren().addAll(heurePlusPollueeTitre,heurePlusPollueeValeur);

        HBox hboxHeureMoinsPolluee = new HBox();
        Text heureMoinsPollueeTitre = new Text("Heure la moins polluee : ");
        heureMoinsPollueeValeur = new Text("");
        hboxHeureMoinsPolluee.getChildren().addAll(heureMoinsPollueeTitre,heureMoinsPollueeValeur);

        HBox hboxHeurePlusDeTest = new HBox();
        Text heurePlusDeTestTitre = new Text("Heure avec le plus de test : ");
        heurePlusDeTestValeur = new Text("");
        hboxHeurePlusDeTest.getChildren().addAll(heurePlusDeTestTitre,heurePlusDeTestValeur);

        HBox hboxHeureMoinsDeTest = new HBox();
        Text heureMoinsDeTestTitre = new Text("Heure avec le moins de tests : ");
        heureMoinsDeTestValeur = new Text("");
        hboxHeureMoinsDeTest.getChildren().addAll(heureMoinsDeTestTitre,heureMoinsDeTestValeur);

        vboxSyntheseHeures.getChildren().addAll(hboxHeurePlusPolluee,hboxHeureMoinsPolluee,hboxHeurePlusDeTest,hboxHeureMoinsDeTest);

        VBox vboxBoutons = new VBox();
        Button actionNaviguerStatistiquesMois = new Button();
        actionNaviguerStatistiquesMois.setText("Statistiques par mois");
        actionNaviguerStatistiquesMois.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                controleur.notifierNaviguerVueMois();
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
        vboxBoutons.getChildren().addAll(actionNaviguerStatistiquesMois,actionNaviguerStatistiquesAnnee);

        vboxDroite.getChildren().addAll(valeurJourTitre,vboxValeursDujour,syntheseHeuresTitre,vboxSyntheseHeures,vboxBoutons);
        vboxDroite.setPadding(new Insets(10,10,10,10));
        vboxDroite.setSpacing(3);
        centre.getChildren().addAll(vboxGauche,vboxDroite);

        vboxBoutons.setSpacing(3);
        vboxDroite.setSpacing(3);

        vboxPrincipal.getChildren().add(centre);
        vboxPrincipal.setPadding(new Insets(10,10,10,10));
        racine.getChildren().add(vboxPrincipal);
        /*****************************************************************/

    }

    public void listerTests(StatistiqueJour statistiqueJour)
    {
        vboxListeHeures.getChildren().clear();

        textNombreTestValeur.setText(statistiqueJour.getNombreDeTest());
        moyenneValeursValeur.setText(statistiqueJour.getSynthese().getMoyenneJour());

        valeurPlusEleveeValeur.setText(statistiqueJour.getSynthese().getMaximumJour());
        valeurPlusFaibleValeur.setText(statistiqueJour.getSynthese().getMinimumJour());

        heurePlusPollueeValeur.setText(statistiqueJour.getSynthese().getHeureValeurMax());
        heureMoinsPollueeValeur.setText(statistiqueJour.getSynthese().getHeureValeurMin());

        heurePlusDeTestValeur.setText(statistiqueJour.getSynthese().getHeureMaxTests());
        heureMoinsDeTestValeur.setText(statistiqueJour.getSynthese().getHeureMinTests());

        for (Heure heure:statistiqueJour.getHeures())
        {
            HBox hboxPrincipalHeure = new HBox();

            VBox vboxHeure = new VBox();
            Text textHeure = new Text("Heure");
            Text textValeurHeure = new Text(heure.getHoraire());
            vboxHeure.getChildren().addAll(textHeure,textValeurHeure);
            vboxHeure.setAlignment(Pos.CENTER);

            VBox vBoxValeursHeure = new VBox();
            Text textTitreValeurs = new Text("Valeurs (en g/m3)");

            HBox hboxPrincipalValeurs = new HBox();

            VBox vboxMoyenneHeure = new VBox();
            Text textMoyenneTitre = new Text("Moyenne");
            Text textMoyenneValeur = new Text(heure.getMoyenne());
            vboxMoyenneHeure.getChildren().addAll(textMoyenneTitre,textMoyenneValeur);

            VBox vboxMaximumHeure = new VBox();
            Text textMaximumTitre = new Text("Maximum");
            Text textMaximumValeur = new Text(heure.getMaximum());
            vboxMaximumHeure.getChildren().addAll(textMaximumTitre,textMaximumValeur);

            VBox vboxMinimumHeure = new VBox();
            Text textMinimumTitre = new Text("Minimum");
            Text textMinimumValeur = new Text(heure.getMinimum());
            vboxMinimumHeure.getChildren().addAll(textMinimumTitre,textMinimumValeur);

            hboxPrincipalValeurs.getChildren().addAll(vboxMoyenneHeure,vboxMaximumHeure,vboxMinimumHeure);
            hboxPrincipalValeurs.setSpacing(20);

            vBoxValeursHeure.getChildren().addAll(textTitreValeurs,hboxPrincipalValeurs);
            vBoxValeursHeure.setAlignment(Pos.CENTER);
            hboxPrincipalHeure.setSpacing(20);
            hboxPrincipalHeure.setPadding(new Insets(0,0,10,0));

            hboxPrincipalHeure.getChildren().addAll(vboxHeure,vBoxValeursHeure);
            hboxPrincipalHeure.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                    + "-fx-border-width: 1;" + "-fx-border-insets: 5;"
                    + "-fx-border-radius: 5;" + "-fx-border-color: blue;");
            vboxListeHeures.getChildren().add(hboxPrincipalHeure);
            scrollPanelisteTest.setContent(vboxListeHeures);
            scrollPanelisteTest.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);


            //System.out.println(heure.getHoraire());

        }

    }

    public void setControleur(Controleur controleur)
    {
        this.controleur = controleur;
    }

}
