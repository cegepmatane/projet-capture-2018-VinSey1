package ca.qc.cgmatane.informatique.projet_capture_2018_vinsey1.vue;

import android.content.Context;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import ca.qc.cgmatane.informatique.projet_capture_2018_vinsey1.R;
import ca.qc.cgmatane.informatique.projet_capture_2018_vinsey1.donnee.CapteurDAO;
import ca.qc.cgmatane.informatique.projet_capture_2018_vinsey1.modele.Heure;

public class VueJour extends AppCompatActivity {

    protected ListView vueListeDonnes;
    private static CapteurDAO accesseurCapteur;
    protected List<HashMap<String, String>> listeHeuresPourAdapteur;
    private Handler gestionnaireVue;
    private boolean alerteActivee = false;
    private boolean alerteValeursActivee = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_jour);

        accesseurCapteur = CapteurDAO.getInstance();

        vueListeDonnes = findViewById(R.id.vue_liste_donnees);
        gestionnaireTailleListe(vueListeDonnes);

        afficherToutesLesHeures();

        TextView jour = findViewById(R.id.valeur_jour);
        TextView nombreTests = findViewById(R.id.valeur_tests);
        TextView moyenne = findViewById(R.id.valeur_moyenne);
        TextView maximum = findViewById(R.id.valeur_maximum);
        TextView minimum = findViewById(R.id.valeur_minimum);
        TextView heureMinimum = findViewById(R.id.valeur_heure_minimum);
        TextView heureMaximum = findViewById(R.id.valeur_heure_maximum);
        TextView heureMinimumTests = findViewById(R.id.valeur_heure_minimum_tests);
        TextView heureMaximumTests = findViewById(R.id.valeur_heure_maximum_tests);

        jour.setText(accesseurCapteur.getJour());
        nombreTests.setText(accesseurCapteur.getNombreTests());
        moyenne.setText(accesseurCapteur.getMoyenneJour());
        maximum.setText(accesseurCapteur.getMaximumJour());
        minimum.setText(accesseurCapteur.getMinimumJour());
        heureMaximum.setText(accesseurCapteur.getHeureValeurMax());
        heureMinimum.setText(accesseurCapteur.getHeureValeurMin());
        heureMinimumTests.setText(accesseurCapteur.getHeureMinimumTests());
        heureMaximumTests.setText(accesseurCapteur.getHeureMaximumTests());

        Timer delaiTempsAlerte = new Timer();

        gestionnaireVue = new Handler();

        delaiTempsAlerte.schedule(new TimerTask() {
            @Override
            public void run() {
                List<Boolean> alertes = accesseurCapteur.alertesCapteur();
                if(!alertes.get(0) && !alerteActivee){
                    alerteActivee = true;
                    gestionnaireVue.post(new Runnable() {
                        @Override
                        public void run() {
                            AlertDialog.Builder alerte = new AlertDialog.Builder(VueJour.this);
                            alerte.setCancelable(true);
                            alerte.setTitle("Attention !");
                            alerte.setMessage("Le capteur est désactivé");
                            AlertDialog dialogue = alerte.create();
                            dialogue.show();
                        }
                    });
                }
                if(alertes.get(1) && !alerteValeursActivee){
                    alerteValeursActivee = true;
                    gestionnaireVue.post(new Runnable() {
                        @Override
                        public void run() {
                            AlertDialog.Builder alerte = new AlertDialog.Builder(VueJour.this);
                            alerte.setCancelable(true);
                            alerte.setTitle("Attention !");
                            alerte.setMessage("Valeurs dangereuses");
                            AlertDialog dialogue = alerte.create();
                            dialogue.show();
                        }
                    });
                }
            }
        }, 3000, 3000);
    }

    private void afficherToutesLesHeures() {
        listeHeuresPourAdapteur = accesseurCapteur.recupererListeHeuresPourAdapteur();

        HeureAdapteur adapteur = new HeureAdapteur(this, accesseurCapteur.getListeHeures());

        vueListeDonnes.setAdapter(adapteur);
    }

    private static void gestionnaireTailleListe(ListView listView) {
        ListAdapter adapteurListe = listView.getAdapter();
        if (adapteurListe == null){
            return;
        }
        int largeur = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int hauteurTotale = 0;
        View vue = null;
        for (int i = 0; i < adapteurListe.getCount(); i++) {
            vue = adapteurListe.getView(i, vue, listView);
            if (i == 0){
                vue.setLayoutParams(new ViewGroup.LayoutParams(largeur, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            vue.measure(largeur, View.MeasureSpec.UNSPECIFIED);
            hauteurTotale += vue.getMeasuredHeight();
        }
        ViewGroup.LayoutParams parametres = listView.getLayoutParams();
        parametres.height = hauteurTotale + (listView.getDividerHeight() * (adapteurListe.getCount() - 1));
        listView.setLayoutParams(parametres);
    }

    protected class HeureAdapteur extends BaseAdapter {

        private List<Heure> listeHeures;
        private LayoutInflater gestionnairePagination;


        public HeureAdapteur(Context contexte, List<Heure> listeHeures){
            this.listeHeures = listeHeures;
            this.gestionnairePagination = LayoutInflater.from(contexte);
        }
        @Override
        public int getCount() {
            return listeHeures.size();
        }

        @Override
        public Object getItem(int position) {
            return listeHeures.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View vueConvertie, ViewGroup parent) {
            ConstraintLayout style;
            if (vueConvertie == null) {
                style = (ConstraintLayout) gestionnairePagination.inflate(R.layout.style_liste, parent, false);
            } else {
                style = (ConstraintLayout) vueConvertie;
            }

            TextView heure = (TextView) style.findViewById(R.id.valeur_heure);
            TextView moyenne = (TextView) style.findViewById(R.id.valeur_moyenne);
            TextView maximum = (TextView) style.findViewById(R.id.valeur_maximum);
            TextView minimum = (TextView) style.findViewById(R.id.valeur_minimum);

            heure.setText(listeHeures.get(position).getHoraire());
            moyenne.setText(listeHeures.get(position).getMoyenne());
            maximum.setText(listeHeures.get(position).getMaximum());
            minimum.setText(listeHeures.get(position).getMinimum());

            return style;
        }
    }
}
