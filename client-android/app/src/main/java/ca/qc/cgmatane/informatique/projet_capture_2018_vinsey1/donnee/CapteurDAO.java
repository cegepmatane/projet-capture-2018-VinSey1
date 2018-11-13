package ca.qc.cgmatane.informatique.projet_capture_2018_vinsey1.donnee;

public class CapteurDAO {

    private static CapteurDAO instance = null;

    public static CapteurDAO getInstance(){
        if(null == instance){
            instance = new CapteurDAO();
        }
        return instance;
    }
}