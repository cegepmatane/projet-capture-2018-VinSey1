package modele;

import java.util.ArrayList;

public class StatistiqueJour {

    private String jour;
    private String nombreDeTest;
    private ArrayList<Heure> heures;
    private Synthese synthese;

    public StatistiqueJour()
    {

    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public String getNombreDeTest() {
        return nombreDeTest;
    }

    public void setNombreDeTest(String nombreDeTest) {
        this.nombreDeTest = nombreDeTest;
    }

    public ArrayList<Heure> getHeures() {
        return heures;
    }

    public void setHeures(ArrayList<Heure> heures) {
        this.heures = heures;
    }

    public Synthese getSynthese() {
        return synthese;
    }

    public void setSynthese(Synthese synthese) {
        this.synthese = synthese;
    }
}
