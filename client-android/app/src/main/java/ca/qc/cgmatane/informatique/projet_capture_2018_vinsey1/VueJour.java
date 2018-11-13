package ca.qc.cgmatane.informatique.projet_capture_2018_vinsey1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class VueJour extends AppCompatActivity {

    protected ListView vueListeDonnes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_jour);
        vueListeDonnes = findViewById(R.id.vue_liste_donnees);
        gestionnaireTailleListe(vueListeDonnes);
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
}
