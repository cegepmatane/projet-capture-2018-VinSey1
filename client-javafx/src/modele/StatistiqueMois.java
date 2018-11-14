package modele;

import java.util.ArrayList;

public class StatistiqueMois {

    private String mois;
    private String nombreTests;
    private ArrayList<Jour> jours;
    private Synthese synthese;

    public StatistiqueMois()
    {

    }

    public String getMois() {
        return mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    public String getNombreTests() {
        return nombreTests;
    }

    public void setNombreTests(String nombreTests) {
        this.nombreTests = nombreTests;
    }

    public ArrayList<Jour> getJours() {
        return jours;
    }

    public void setJours(ArrayList<Jour> jours) {
        this.jours = jours;
    }

    public Synthese getSynthese() {
        return synthese;
    }

    public void setSynthese(Synthese synthese) {
        this.synthese = synthese;
    }
}
