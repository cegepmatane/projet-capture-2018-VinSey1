package ca.qc.cgmatane.informatique.projet_capture_2018_vinsey1.modele;

import java.util.HashMap;

public class Heure {

    private String horaire, moyenne, maximum, minimum;

    public Heure(String horaire, String moyenne, String maximum, String minimum){
        this.horaire = horaire;
        this.moyenne = moyenne;
        this.maximum = maximum;
        this.minimum = minimum;
    }

    public String getHoraire() {
        return horaire;
    }

    public String getMoyenne() {
        return moyenne;
    }

    public String getMaximum() {
        return maximum;
    }

    public String getMinimum() {
        return minimum;
    }

    public HashMap<String,String> obtenirHeurePourAdapteur() {
        HashMap<String, String> heurePourAdapteur = new HashMap<String, String>();
        heurePourAdapteur.put("horaire", this.horaire);
        heurePourAdapteur.put("moyenne", this.moyenne);
        heurePourAdapteur.put("maximum", this.maximum);
        heurePourAdapteur.put("minimum", this.minimum);
        return heurePourAdapteur;
    }
}