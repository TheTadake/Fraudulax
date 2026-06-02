package system.fraude;

import java.time.LocalDate;

public class FraudeIAGConnecte extends FraudeIAG {
    private String adresseIP;

    public FraudeIAGConnecte(LocalDate date, String contenu, String descriptionTextuelle, String adresseIP) {
        super(date,contenu,descriptionTextuelle);
        this.adresseIP = adresseIP;
    }

    public String getAdresseIP(){ return adresseIP ;}
    public void setAdresseIP(String adresseIP){ this.adresseIP = adresseIP; }
}
