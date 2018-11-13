package ca.qc.cgmatane.informatique.projet_capture_2018_vinsey1.donnee;

public class CapteurDAO {

    private static CapteurDAO instance = null;
    private ServiceDAO accesseurService;

    public static CapteurDAO getInstance(){
        if(null == instance){
            instance = new CapteurDAO();
        }
        return instance;
    }

    public CapteurDAO{
        accesseurService = new ServiceDAO();
    }
}