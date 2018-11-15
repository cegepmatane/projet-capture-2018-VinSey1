package modele;

import java.util.ArrayList;

public class StatistiqueAnnee {
    private String annee;
    private String nombreTests;
    private ArrayList<Mois> mois;
    private Synthese synthese;

    public StatistiqueAnnee()
    {

    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public String getNombreTests() {
        return nombreTests;
    }

    public void setNombreTests(String nombreTests) {
        this.nombreTests = nombreTests;
    }

    public ArrayList<Mois> getMois() {
        return mois;
    }

    public void setMois(ArrayList<Mois> mois) {
        this.mois = mois;
    }

    public Synthese getSynthese() {
        return synthese;
    }

    public void setSynthese(Synthese synthese) {
        this.synthese = synthese;
    }
}
