package modele;

public class Synthese {
    private String moyenneJour;
    private String maximumJour;
    private String minimumJour;
    private String heureValeurMax;
    private String heureValeurMin;
    private String heureMaxTests;
    private String heureMinTests;


    public Synthese()
    {

    }

    public String getMoyenneJour() {
        return moyenneJour;
    }

    public void setMoyenneJour(String moyenneJour) {
        this.moyenneJour = moyenneJour;
    }

    public String getMaximumJour() {
        return maximumJour;
    }

    public void setMaximumJour(String maximumJour) {
        this.maximumJour = maximumJour;
    }

    public String getMinimumJour() {
        return minimumJour;
    }

    public void setMinimumJour(String minimumJour) {
        this.minimumJour = minimumJour;
    }

    public String getHeureValeurMax() {
        return heureValeurMax;
    }

    public void setHeureValeurMax(String heureValeurMax) {
        this.heureValeurMax = heureValeurMax;
    }

    public String getHeureValeurMin() {
        return heureValeurMin;
    }

    public void setHeureValeurMin(String heureValeurMin) {
        this.heureValeurMin = heureValeurMin;
    }

    public String getHeureMaxTests() {
        return heureMaxTests;
    }

    public void setHeureMaxTests(String heureMaxTests) {
        this.heureMaxTests = heureMaxTests;
    }

    public String getHeureMinTests() {
        return heureMinTests;
    }

    public void setHeureMinTests(String heureMinTests) {
        this.heureMinTests = heureMinTests;
    }
}
