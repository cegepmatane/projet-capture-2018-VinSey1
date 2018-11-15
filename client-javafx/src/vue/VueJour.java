package vue;

import action.Controleur;
import javafx.application.Application;
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
import modele.Heure;
import modele.StatistiqueJour;

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
    protected Text textNombreTest;
    protected VBox vboxListeHeures;
    private LocalDate date = null;

    private Controleur controleur = null;


    public VueJour()
    {
        super(new StackPane(),800,800);
        racine = (StackPane) this.getRoot();
    }

    public void afficherVueJour()
    {
        vboxPrincipal = new VBox();
        /*****************************************************************/
        //TITRE - Partie du Haut

        HBox hboxTitre = new HBox();
        VBox vboxTitre = new VBox();
        vboxListeHeures = new VBox();

        Text titreHaut = new Text("Statistiques de l'application");
        Text titreBas = new Text("Par jour");
        vboxTitre.getChildren().addAll(titreHaut,titreBas);
        hboxTitre.getChildren().add(vboxTitre);
        vboxPrincipal.getChildren().add(hboxTitre);

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
                //ACTION AFFICHER LISTE AVEC DATE
                date = selectionDate.getValue();
                Instant instant = Instant.from(date.atStartOfDay(ZoneId.systemDefault()));
                Date dateFormatee = Date.from(instant);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = dateFormat.format(dateFormatee);
                String annee = strDate.split("-")[0];
                String mois = strDate.split("-")[1];
                String jour = strDate.split("-")[2];
                //controleur.notifierChangementDate(annee,mois,jour);
                controleur.notifierChangementDate("2032","04","20");
            }
        });
        //TODO SELECTION DATE


        jour.getChildren().addAll(textJour,selectionDate);
        textNombreTest = new Text("Nombre de test : ");
        //TODO Ajouter valeur
        /*****************************************************************/
        //LISTE VALEURS TODO AJOUTER VALEURS
        scrollPanelisteTest = new ScrollPane();
        vboxGauche.getChildren().addAll(jour,actionAfficherListe,textNombreTest,scrollPanelisteTest);
        /*****************************************************************/
        //Valeur du jour + Synthese des heures et Boutons - Partie Droite

        VBox vboxDroite = new VBox();
        Text valeurJourTitre = new Text("Valeurs du jour");
        VBox vboxValeursDujour = new VBox();

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
        vboxValeursDujour.getChildren().addAll(hboxValeurMoyenne,hboxValeurPlusElevee,hboxValeurPlusFaible);


        Text syntheseHeuresTitre = new Text("Synthese des heures");
        VBox vboxSyntheseHeures = new VBox();

        HBox hboxHeurePlusPolluee = new HBox();
        Text heurePlusPollueeTitre = new Text("Heure la plus polluee : ");
        Text heurePlusPollueeValeur = new Text("10" + "h");
        hboxHeurePlusPolluee.getChildren().addAll(heurePlusPollueeTitre,heurePlusPollueeValeur);

        HBox hboxHeureMoinsPolluee = new HBox();
        Text heureMoinsPollueeTitre = new Text("Heure la moins polluee : ");
        Text heureMoinsPollueeValeur = new Text("8" + "h");
        hboxHeureMoinsPolluee.getChildren().addAll(heureMoinsPollueeTitre,heureMoinsPollueeValeur);

        HBox hboxHeurePlusDeTest = new HBox();
        Text heurePlusDeTestTitre = new Text("Heure avec le plus de test : ");
        Text heurePlusDeTestValeur = new Text("15" + "h");
        hboxHeurePlusDeTest.getChildren().addAll(heurePlusDeTestTitre,heurePlusDeTestValeur);

        HBox hboxHeureMoinsDeTest = new HBox();
        Text heureMoinsDeTestTitre = new Text("Heure avec le moins de tests : ");
        Text heureMoinsDeTestValeur = new Text("16" + "h");
        hboxHeureMoinsDeTest.getChildren().addAll(heureMoinsDeTestTitre,heureMoinsDeTestValeur);

        //TODO Ajouter les valeurs
        vboxSyntheseHeures.getChildren().addAll(hboxHeurePlusPolluee,hboxHeureMoinsPolluee,hboxHeurePlusDeTest,hboxHeureMoinsDeTest);

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

        vboxDroite.getChildren().addAll(valeurJourTitre,vboxValeursDujour,syntheseHeuresTitre,vboxSyntheseHeures,vboxBoutons);

        centre.getChildren().addAll(vboxGauche,vboxDroite);

        vboxPrincipal.getChildren().add(centre);
        racine.getChildren().add(vboxPrincipal);
        /*****************************************************************/

    }

    public void listerTests(StatistiqueJour statistiqueJour)
    {
        for (Heure heure:statistiqueJour.getHeures())
        {
            HBox hboxPrincipalHeure = new HBox();

            VBox vboxHeure = new VBox();
            Text textHeure = new Text("Heure");
            Text textValeurHeure = new Text(heure.getHoraire());
            vboxHeure.getChildren().addAll(textHeure,textValeurHeure);

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

            vBoxValeursHeure.getChildren().addAll(textTitreValeurs,hboxPrincipalValeurs);

            hboxPrincipalHeure.getChildren().addAll(vboxHeure,vBoxValeursHeure);
            vboxListeHeures.getChildren().add(hboxPrincipalHeure);
            scrollPanelisteTest.setContent(vboxListeHeures);

            System.out.println(heure.getHoraire());

        }

    }

    public void setControleur(Controleur controleur)
    {
        this.controleur = controleur;
    }

}
