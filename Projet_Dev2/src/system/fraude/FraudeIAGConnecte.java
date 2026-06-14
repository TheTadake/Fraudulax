package system.fraude;

import java.time.LocalDate;
/**
 * @brief  Classe  représentant une fraude d'usage d'une IAG connecté à internet.
 */
public class FraudeIAGConnecte extends FraudeIAG {
    private String adresseIP;

    /**
     * @brief Constructeur de la classe FraudeIAGConnecte.
     * @param date
     * @param contenu
     * @param descriptionTextuelle
     * @param adresseIP
     */
    public FraudeIAGConnecte(LocalDate date, String contenu, String descriptionTextuelle, String adresseIP) {
        super(date,contenu,descriptionTextuelle);
        this.adresseIP = adresseIP;
    }

    public String getAdresseIP(){ return adresseIP ;}
    public void setAdresseIP(String adresseIP){ this.adresseIP = adresseIP; }
}
